package ghost.svc.db;

import ghost.svc.model.Comment;

import java.util.List;

/**
 * Data access of {@link Comment}
 */
public interface CommentsDao {

    Comment save(final Comment comment);

    Comment load(final String commentId);

    List<Comment> loadByS2Cell(String s2cell);
}
