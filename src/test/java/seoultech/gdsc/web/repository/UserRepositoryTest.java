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
        this.newUser.setName("Yubin Shin");
        this.newUser.setEmail("dbqls2821@gmail.com");
        this.newUser.setUserId("shinyubin989");
        this.newUser.setHp("010-8614-2841");
        this.newUser.setMajor("computer engineering");
        this.newUser.setPassword("28782821");
        this.newUser.setNickname("yubin");

        this.userRepository.save(newUser);
    }
}

