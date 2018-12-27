package ghost.svc.db;

import ghost.svc.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    public List<Comment> findAllByCellToken(String l15S2CellToken);
}
