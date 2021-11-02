package seoultech.gdsc.web.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import seoultech.gdsc.web.WebApplicationTests;
import seoultech.gdsc.web.entity.Message;
import seoultech.gdsc.web.entity.User;

import javax.transaction.Transactional;

@Transactional
@SpringBootTest
public class MessageRepositoryTest extends WebApplicationTests {
    @Autowired
    private MessageRepository messageRepository;
    private Message newMessage;
    private User newUser;

    @Test
    @Transactional
    public void saveMessageTest(){
        newMessage = new Message();
        this.newMessage.setContent("new Content");
        this.newMessage.setTo(newUser);
        this.newMessage.setFrom(newUser);

        this.messageRepository.save(newMessage);

    }
}
