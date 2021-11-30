package seoultech.gdsc.web.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import seoultech.gdsc.web.dto.LikedDto;
import seoultech.gdsc.web.entity.Board;
import seoultech.gdsc.web.entity.Comment;
import seoultech.gdsc.web.entity.Liked;
import seoultech.gdsc.web.repository.BoardRepository;
import seoultech.gdsc.web.repository.CommentRepository;
import seoultech.gdsc.web.repository.LikedRepository;
import seoultech.gdsc.web.repository.UserRepository;

import java.util.Optional;

@Service
public class LikedService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final LikedRepository likedRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public LikedService(UserRepository userRepository,
                        ModelMapper modelMapper,
                        LikedRepository likedRepository,
                        BoardRepository boardRepository,
                        CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.likedRepository = likedRepository;
        this.boardRepository = boardRepository;
        this.commentRepository = commentRepository;
    }

    public String clickLike(Integer sessionId, LikedDto.Request request){
        /*
        <request>
        category : 카테고리 번호 (1은 게시글, 2는 댓글)
        ref_id : 게시글/댓글의 고유 번호
         */

        /*
        이미 좋아요를 눌렀던 글인 경우
        {
              sucess:false,
              data: {},
              message: "이미 공감한 글입니다"
        }
        이미 좋아요를 눌렀던 댓글인 경우
        {
              sucess:false,
              data: {},
              message: "이미 공감한 댓글입니다"
        }
         */
        /*
            - 글 또는 댓글의 좋아요 누른 경우
            - category 가 1이면 게시글, 2이면 댓글
            - ref_id 는 reference id, 즉 category가 1이였을 땐, 좋아요를 누른 글의 id
                                                    2였을 땐, 좋아요를 누른 댓글의 id
            - 눌렀을 때, 이미누른 적이 있다면 fail 처리
            - 최초 공감 일 시에만 좋아요 반영
            - 공감 수가 10이 넘어가면 is_hot 을 true로 바꿔야 함
         */
        Optional<Liked> isExist = likedRepository.findByUser_IdAndLikeCategoryAndRefId(sessionId, request.getCategory(), request.getRefId());

        if(isExist.isPresent()){
            if(request.getCategory() == 1){
                return "이미 공감한 글입니다.";
            }
            else{
                return "이미 공감한 댓글입니다.";
            }
        }

        if(request.getCategory() == 1){
            Board board = boardRepository.getById(request.getRefId());
            board.setLikeNum(board.getLikeNum()+1);
            if(board.getLikeNum() == 10){
                board.setIsHot(true);
            }
        }
        else{
            Comment comment = commentRepository.getById(request.getRefId());
            comment.setLikeNum(comment.getLikeNum()+1);
        }
        return "";
    }



}
