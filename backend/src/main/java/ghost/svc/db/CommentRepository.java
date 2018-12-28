package ghost.svc.db;

import ghost.svc.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    public List<Comment> findAllByCellToken(String l15S2CellToken);

    //
    //@Query(value = "select * from #{#entityName} c where c.cell_token = ?1", nativeQuery = true)
    @Query(value = "from Comment c  where c.cellToken = :celltoken")
    public Page<Comment> findByCellToken(Pageable pageable, @Param("celltoken") String cellToken);
}
