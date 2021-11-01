package seoultech.gdsc.web.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import seoultech.gdsc.web.WebApplicationTests;
import seoultech.gdsc.web.entity.Board;
import seoultech.gdsc.web.entity.Comment;
import seoultech.gdsc.web.entity.User;

@SpringBootTest
public class CommentRepositoryTest extends WebApplicationTests {

    @Autowired
    private CommentRepository commentRepository;

    private Comment newComment;
    private User newUser;
    private Board newBoard;


    @Test
    public void saveCommentTest(){
        newComment = new Comment();
        newUser = new User();
        newBoard = new Board();
        this.newComment.setBoardId(newBoard);
        this.newComment.setUserId(newUser);
        this.newComment.setContent("new content");
        this.commentRepository.save(newComment);
    }

}
