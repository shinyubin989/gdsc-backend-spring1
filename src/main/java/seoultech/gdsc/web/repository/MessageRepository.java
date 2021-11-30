package seoultech.gdsc.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import seoultech.gdsc.web.entity.Liked;
import seoultech.gdsc.web.entity.Message;
import seoultech.gdsc.web.entity.User;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findAllMessageByFrom(int userId);
    List<Message> findAllMessageByTo(int userId);

}


/**
 *     @ManyToOne(targetEntity = User.class)
 *     private User from;
 *
 *     @ManyToOne(targetEntity = User.class)
 *     private User to;
 *
 *     @Column(nullable = false)
 *     private String content;
 */
