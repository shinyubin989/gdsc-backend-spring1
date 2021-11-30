package seoultech.gdsc.web.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seoultech.gdsc.web.dto.CommentDto;
import seoultech.gdsc.web.entity.Board;
import seoultech.gdsc.web.entity.Comment;
import seoultech.gdsc.web.repository.BoardCategoryRepository;
import seoultech.gdsc.web.repository.BoardRepository;
import seoultech.gdsc.web.repository.CommentRepository;
import seoultech.gdsc.web.repository.UserRepository;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CommentService {
    private final ModelMapper modelMapper;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Autowired
    public CommentService(ModelMapper modelMapper,
                          CommentRepository commentRepository,
                          UserRepository userRepository,
                          BoardRepository boardRepository){
        this.modelMapper = modelMapper;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
    }


    //mapComment -> getComments
    public List<CommentDto.Response> getComments(int boardId){
        List<Comment> comments = commentRepository.findAllByBoard_Id(boardId);
        List<CommentDto.Response> responses = null;
        for(int i=0; i<comments.size(); i++){
            responses.add(mapComment(comments.get(i)));
        }
        return responses;
    }
    public CommentDto.Response mapComment(Comment comment){
        CommentDto.Response response = modelMapper.map(comment, CommentDto.Response.class);
        response.setCreatedAt(comment.getCreatedAt().format(DateTimeFormatter.ofPattern("yyMMddhhmm")));
        userRepository.findById(comment.getUser().getId()).ifPresent(user -> {
            response.setUserId(user.getId()); });
        if(comment.getIsSecret()){
            response.setProfilePic("익명사진.png");
            response.setNickname("익명");
        }else{
            response.setProfilePic(comment.getUser().getProfilePic());
            response.setNickname(comment.getUser().getNickname());
        }
        return response;
    }


    public void postNewComment(int id, CommentDto.Request request){
        Comment comment = modelMapper.map(request, Comment.class);
        Board board = boardRepository.getById(request.getBoardId());
        userRepository.findById(id).ifPresent(comment::setUser);
        comment.setBoard(board);
        board.setCommentNum(board.getCommentNum()+1);
        boardRepository.save(board);
        commentRepository.save(comment);
    }
}
