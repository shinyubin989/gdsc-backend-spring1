package seoultech.gdsc.web.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import seoultech.gdsc.web.dto.BoardDto;
import seoultech.gdsc.web.dto.CommentDto;
import seoultech.gdsc.web.entity.Board;
import seoultech.gdsc.web.entity.BoardCategory;
import seoultech.gdsc.web.entity.Comment;
import seoultech.gdsc.web.entity.User;
import seoultech.gdsc.web.repository.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;
    private final BoardCategoryRepository boardCategoryRepository;

    @Autowired
    public BoardService(UserRepository userRepository,
                        BoardRepository boardRepository,
                        ModelMapper modelMapper,
                        BoardCategoryRepository boardCategoryRepository){
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
        this.modelMapper = modelMapper;
        this.boardCategoryRepository = boardCategoryRepository;
    }

    public void addBoard(int id, BoardDto.Request board){
        Board newBoard = modelMapper.map(board, Board.class);
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            newBoard.setUser(user.get());
        }
        boardCategoryRepository.findById(board.getCategoryId()).ifPresent(newBoard::setBoardCategory);
        boardRepository.save(newBoard);
    }


    public List<BoardDto.Response> getBoard(int categoryId){
        List<Board> boards;
        if(categoryId == 7){
            boards = boardRepository.findAllByIsHotOrderByCreatedAtDesc(true);
        }
        else{
            boards = boardRepository.findAllByBoardCategory_Id(categoryId);
        }
        List<BoardDto.Response> responses = null;
        for(int i=0; i<boards.size(); i++){
            responses.add(mapBoard(boards.get(i)));
        }
        return responses;
    }
    public BoardDto.Response mapBoard(Board board){
        BoardDto.Response response = modelMapper.map(board, BoardDto.Response.class);

        response.setBoardCategoryId(board.getBoardCategory().getId());
        response.setCreatedAt(board.getCreatedAt().format(DateTimeFormatter.ofPattern("yyMMdd")));
        return response;
    }



    public BoardDto.DetailResponse getBoardDetail(int boardId){
        Board board = boardRepository.getById(boardId);
        BoardDto.DetailResponse response = modelMapper.map(board, BoardDto.DetailResponse.class);
        if(board.getIsSecret()){
            response.setNickname("익명");
            response.setProfilePic("익명프로필사진");
        }
        else{
            response.setNickname(board.getUser().getNickname());
            response.setProfilePic(board.getUser().getProfilePic());
        }
        return response;
    }


    public List<BoardDto.myBoardResponse> getMyBoard(){
        List<Board> myBoards = boardRepository.findMyBoard();
        List<BoardDto.myBoardResponse> responses = null;
        for(int i=0; i<myBoards.size(); i++){
            responses.add(mapMyBoard(myBoards.get(i)));
        }
        return responses;
    }
    public BoardDto.myBoardResponse mapMyBoard(Board board){
        BoardDto.myBoardResponse response = modelMapper.map(board, BoardDto.myBoardResponse.class);
        response.setCreatedAt(board.getCreatedAt().format(DateTimeFormatter.ofPattern("yyMMdd")));
        response.setBoardCategoryId(board.getBoardCategory().getId());
        return response;
    }


    public List<BoardDto.HotBoardResponse> getHotBoard(){
        List<Board> hotBoards = boardRepository.findTop2ByIsHotOrderByCreatedAtDesc(true);
        List<BoardDto.HotBoardResponse> responses = null;
        for(int i=0; i<hotBoards.size(); i++){
            responses.add(mapHotBoard(hotBoards.get(i)));
        }
        return responses;
    }
    public BoardDto.HotBoardResponse mapHotBoard(Board board){
        BoardDto.HotBoardResponse response = modelMapper.map(board, BoardDto.HotBoardResponse.class);
        response.setCreatedAt(board.getCreatedAt().format(DateTimeFormatter.ofPattern("yyMMdd")));
        return response;
    }


    public List<BoardDto.RealtimeResponse> getRealtimeBoard(){
        List<Board> realTimeBoards = boardRepository.findRealtimeBoard();
        List<BoardDto.RealtimeResponse> responses = null;
        for(int i=0; i<realTimeBoards.size(); i++){
            responses.add(mapRealtimeBoard(realTimeBoards.get(i)));
        }
        return responses;
    }
    public BoardDto.RealtimeResponse mapRealtimeBoard(Board board){
        BoardDto.RealtimeResponse response = modelMapper.map(board, BoardDto.RealtimeResponse.class);
        if (board.getIsSecret()) {
            response.setProfilePic("익명프사");
            response.setNickname("익명");
        } else {
            response.setProfilePic(board.getUser().getProfilePic());
            response.setNickname(board.getUser().getNickname());
        }
        return response;
    }


    public List<BoardDto.FilteredBoardResponse> getFilteredBoard(int category, boolean isHot){
        List<Board> filteredBoards;
        if (isHot) {
            filteredBoards = boardRepository.findTop2ByIsHotAndBoardCategory_IdOrderByCreatedAtDesc(isHot, category);
        } else {
            filteredBoards = boardRepository.findTop2ByBoardCategory_IdOrderByCreatedAtDesc(category);
        }
        List<BoardDto.FilteredBoardResponse> responses = null;
        for(int i=0; i<filteredBoards.size(); i++){
            responses.add(mapFilteredBoard(filteredBoards.get(i)));
        }
        return responses;
    }
    public BoardDto.FilteredBoardResponse mapFilteredBoard(Board board){
        BoardDto.FilteredBoardResponse response = modelMapper.map(board, BoardDto.FilteredBoardResponse.class);
        response.setCreatedAt(board.getCreatedAt().format(DateTimeFormatter.ofPattern("yyMMdd")));
        return response;
    }

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



}
