package seoultech.gdsc.web.repository;

import org.junit.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import seoultech.gdsc.web.entity.Board;
import seoultech.gdsc.web.entity.User;
import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    List<Board> findAllBoardByUser_Id(@Param(value = "userId") int userId);

    List<Board> findAllByIsHotOrderByCreatedAtDesc(@Param(value = "isHot") Boolean isHot);

    List<Board> findAllByBoardCategory_Id(@Param(value = "categoryId") int categoryId);

    //- SELECT * FROM boards WHERE board_category_id <= 6 and created_at IN (SELECT MAX(created_at) FROM boards GROUP BY board_category_id)
    //- select * from board where id in (select max(id) from board group by board_category_id);
    @Query("select board from Board board where board.id in (select max(board.id) from board group by board.boardCategory) and board.boardCategory.id <= 6 order by board.boardCategory.id asc")
    List<Board> findMyBoard();

    List<Board> findTop2ByIsHotOrderByCreatedAtDesc(@Param(value = "isHot") Boolean isHot);

    //- SELECT p.*, like_num+comment_num as total, q.user_id, q.profile_pic FROM gdsc_back.boards as p JOIN gdsc_back.users as q ON p.user_id = [q.id](http://q.id/) WHERE is_hot = 1 and p.created_at > DATE_ADD(now(), INTERVAL -24 HOUR) order by total DESC, p.created_at DESC LIMIT 2
    //- select *, like_num+comment_num as total from board where is_hot = 1 and created_at > DATE_ADD(now(), INTERVAL -24 HOUR) order by total DESC, created_at DESC LIMIT 2
    @Query(value = "select *, like_num+comment_num as total from board where is_hot = 1 and created_at > DATE_ADD(now(), INTERVAL -24 HOUR) order by total DESC, created_at DESC LIMIT 2", nativeQuery = true)
    List<Board> findRealtimeBoard();

    List<Board> findTop2ByIsHotAndBoardCategory_IdOrderByCreatedAtDesc(@Param(value = "isHot") Boolean isHot, @Param(value = "boardCategory") int BoardCategory);

    List<Board> findTop2ByBoardCategory_IdOrderByCreatedAtDesc(@Param(value = "boardCategory") int BoardCategory);

    @Query("select board from Board board where board.title like %?1% or board.content like %?2%")
    List<Board> findAllByKeyword(String titleKeyword, String contentKeyword);
    @Query("select board from Board board where board.boardCategory.id = ?1 and (board.title like %?2% or board.content like %?3%)")
    List<Board> findAllByCategoryByKeyword(int categoryId, String boardKeyword, String contentKeyword);
}