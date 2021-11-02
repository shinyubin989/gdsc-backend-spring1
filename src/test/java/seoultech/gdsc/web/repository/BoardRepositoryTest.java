package seoultech.gdsc.web.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import seoultech.gdsc.web.WebApplicationTests;
import seoultech.gdsc.web.entity.Board;
import seoultech.gdsc.web.entity.User;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Transactional
@SpringBootTest
public class BoardRepositoryTest extends WebApplicationTests {

    @Autowired
    private BoardRepository boardRepository;
    private Board newBoard;
    private User newUser;

    @Test
    @Transactional
    public void saveBoardTest() {
        newBoard = new Board();
        newUser = new User();
        this.newBoard.setUser(newUser);
        this.newBoard.setCommentNum(5);
        this.newBoard.setTitle("newTitle");
        this.newBoard.setContent("new content");
        this.newBoard.setCategory(1);
        this.boardRepository.save(newBoard);
    }
}
