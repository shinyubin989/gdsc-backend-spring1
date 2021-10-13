package seoultech.gdsc.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seoultech.gdsc.web.entity.Board;
import seoultech.gdsc.web.entity.User;

import java.util.List;


@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
}
