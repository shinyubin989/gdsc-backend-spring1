package seoultech.gdsc.web.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import seoultech.gdsc.web.WebApplicationTests;
import seoultech.gdsc.web.entity.BoardCategory;

import javax.transaction.Transactional;

@Transactional
@SpringBootTest
public class BoardCategoryRepositoryTest extends WebApplicationTests {
    @Autowired
    private BoardCategoryRepository boardCategoryRepository;
    private BoardCategory newBoardCategory;

    @Test
    @Transactional
    public void saveBoardCategoryTest(){
        newBoardCategory = new BoardCategory();
        this.newBoardCategory.setCategoryName("자유게시판");
        this.boardCategoryRepository.save(newBoardCategory);
    }
}
