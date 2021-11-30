package seoultech.gdsc.web.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seoultech.gdsc.web.dto.MessageDto;
import seoultech.gdsc.web.entity.Message;
import seoultech.gdsc.web.repository.BoardRepository;
import seoultech.gdsc.web.repository.CommentRepository;
import seoultech.gdsc.web.repository.MessageRepository;
import seoultech.gdsc.web.repository.UserRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class MessageService {
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MessageRepository messageRepository;
    private final EntityManager entityManager;
    private final ModelMapper modelMapper;


    @Autowired
    public MessageService(UserRepository userRepository,
                          MessageRepository messageRepository,
                          EntityManager entityManager,
                          ModelMapper modelMapper,
                          CommentRepository commentRepository,
                          BoardRepository boardRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.entityManager = entityManager;
        this.modelMapper = modelMapper;
        this.commentRepository = commentRepository;
        this.boardRepository = boardRepository;
    }
    /*

    public List<MessageDto.Response> getMessages(int sessionId){
        List<Message> messages;

        return messages;
    }

     */

    /*
    public List<BoardDto.SearchResponse> postSearchCategory(int category, String keyword){
        //카테고리 0이면 전체 게시글에서 검색, 그 외는 카테고리 안에서
        List<Board> boards;
        if (category == 0) {
            boards = boardRepository.findAllByKeyword(keyword, keyword);
        } else {
            boards = boardRepository.findAllByCategoryByKeyword(category, keyword, keyword);
        }
        List<BoardDto.SearchResponse> responses = null;
        for(int i=0; i<boards.size(); i++){
            responses.add(mapSearchCategory(boards.get(i)));
        }
        return responses;
    }
    public BoardDto.SearchResponse mapSearchCategory(Board board){
        BoardDto.SearchResponse response = modelMapper.map(board, BoardDto.SearchResponse.class);
        response.setCreatedAt(board.getCreatedAt().format(DateTimeFormatter.ofPattern("yyMMdd")));
        if (board.getIsSecret()) {
            response.setNickName("익명");
        } else {
            response.setNickName(board.getUser().getNickname());
        }
        return response;
    }
     */


}
