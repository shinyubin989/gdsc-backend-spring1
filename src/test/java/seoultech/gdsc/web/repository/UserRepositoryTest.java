package seoultech.gdsc.web.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import seoultech.gdsc.web.WebApplicationTests;
import seoultech.gdsc.web.entity.User;

import javax.transaction.Transactional;


@SpringBootTest
public class UserRepositoryTest extends WebApplicationTests {

    @Autowired
    private UserRepository userRepository;

    private User newUser;

    @Test
    public void saveUserTest(){
        newUser = new User();
        this.newUser.setUserId("twinklesu");
        this.userRepository.save(newUser);
    }
}

