package seoultech.gdsc.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import seoultech.gdsc.web.entity.Comment;
import seoultech.gdsc.web.entity.User;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByUser_Id(@Param(value="userId")int userId);
    List<Comment> findAllByBoard_Id(@Param(value="boardId")int boardId);
}
