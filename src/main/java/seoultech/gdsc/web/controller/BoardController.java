package seoultech.gdsc.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import seoultech.gdsc.web.dto.BoardDto;
import seoultech.gdsc.web.dto.CommentDto;
import seoultech.gdsc.web.response.BasicResponse;
import seoultech.gdsc.web.response.EmptyJsonResponse;
import seoultech.gdsc.web.response.SuccessResponse;
import seoultech.gdsc.web.service.BoardService;
import seoultech.gdsc.web.service.CommentService;

import javax.persistence.Basic;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/board")
public class BoardController {

     private final BoardService boardService;
     private final HttpSession session;
     private final CommentService commentService;

     @Autowired
     public BoardController(BoardService boardService,
                            HttpSession session,
                            CommentService commentService){
          this.boardService = boardService;
          this.session = session;
          this.commentService = commentService;
     }

     @PostMapping("")
     public BasicResponse postBoard(@RequestBody BoardDto.Request board){
          int id = (int) session.getAttribute("springSes");
          boardService.addBoard(id, board);
          return new SuccessResponse<>(new EmptyJsonResponse());
     }

     @GetMapping("")
     public BasicResponse getBoard(@RequestParam int category) {
          List<BoardDto.Response> boardList = boardService.getBoard(category);
          return new SuccessResponse<>(boardList);
     }

     @GetMapping("/detail/{id}")
     public BasicResponse getBoardDetail(@PathVariable int id){
          BoardDto.DetailResponse board = boardService.getBoardDetail(id);
          return new SuccessResponse<>(board);

     }

     @GetMapping("/{id}/comment")
     public BasicResponse getCommentsInBoard(@PathVariable int id){
          List<CommentDto.Response> commentList = commentService.getComments(id);
          return new SuccessResponse<>(commentList);
     }

     @PostMapping("/comment")
     public BasicResponse postComment(@RequestBody CommentDto.Request comment){
          int id = (int) session.getAttribute("springSes");
          commentService.postNewComment(id, comment);
          return new SuccessResponse<>(new EmptyJsonResponse());
     }


     @GetMapping("/main/myboard")
     public BasicResponse getMyBoard(){
          List<BoardDto.myBoardResponse> response = boardService.getMyBoard();
          return new SuccessResponse<>(response);
     }

     @GetMapping("/main/hot")
     public BasicResponse getHotBoard(){
          List<BoardDto.HotBoardResponse> response = boardService.getHotBoard();
          return new SuccessResponse<>(response);
     }

     @GetMapping("/main/realtime")
     public BasicResponse getRealTimeBoard(){
          List<BoardDto.RealtimeResponse> response = boardService.getRealtimeBoard();
          return new SuccessResponse<>(response);

     }

     @GetMapping("/main/filter")
     public BasicResponse get2LatestOrder(@RequestParam(value="category") int category,
                               @RequestParam(value="hot") int hot) {
          boolean isHot = hot != 0;
          List<BoardDto.FilteredBoardResponse> response = boardService.getFilteredBoard(category, isHot);
          return new SuccessResponse<>(response);
     }

     @PostMapping("/search")
     public BasicResponse postSearchCategory(@RequestParam("category") int category,
                                             @RequestParam("keyword") String keyword) {
          //카테고리 0이면 전체 게시글에서 검색, 그 외는 카테고리 안에서
          List<BoardDto.SearchResponse> response = boardService.postSearchCategory(category, keyword);
          return new SuccessResponse<>(response);
     }
}
