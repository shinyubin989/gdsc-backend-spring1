package seoultech.gdsc.web.repository;

import org.junit.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seoultech.gdsc.web.entity.User;

import javax.transaction.Transactional;

@Repository
public interface UserRepository  extends JpaRepository<User, Integer> {

}
