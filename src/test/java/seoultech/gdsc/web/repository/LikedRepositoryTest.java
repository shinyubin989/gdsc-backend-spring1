package seoultech.gdsc.web.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import seoultech.gdsc.web.entity.Liked;
import seoultech.gdsc.web.entity.User;

@SpringBootTest
public class LikedRepositoryTest {
    @Autowired
    private LikedRepository likedRepository;
    private Liked newLiked;
    private User newUser;

    @Test
    public void saveLiked(){
        newLiked = new Liked();
        this.newLiked.setUserId(newUser);
        this.newLiked.setLikeCategory(5);
        this.newLiked.setRefId(3);
        this.likedRepository.save(newLiked);
    }
}
