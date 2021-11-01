package seoultech.gdsc.web.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import seoultech.gdsc.web.entity.BoardCategory;


@SpringBootTest
public class BoardCategoryRepositoryTest {
    @Autowired
    private BoardCategoryRepository boardCategoryRepository;
    private BoardCategory newBoardCategory;

    @Test
    public void saveBoardCategory(){
        newBoardCategory = new BoardCategory();
        this.newBoardCategory.setCategoryName("자유게시판");
        this.boardCategoryRepository.save(newBoardCategory);
    }
}
