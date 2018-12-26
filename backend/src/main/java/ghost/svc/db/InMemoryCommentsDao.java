package ghost.svc.db;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import ghost.svc.model.Comment;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple implementation of {@link CommentsDao} saving all comments in memory.
 */
public class InMemoryCommentsDao implements CommentsDao {
    private static final String COMMENT_ID_PREFIX = "cmt-";

    private Map<String, Comment> commentsMap = new HashMap<>();
    private ListMultimap<String, Comment> commentsMapByS2Cell = ArrayListMultimap.create();

    @Override
    public Comment save(Comment comment) {
        checkNotNull(comment);
        checkArgument(StringUtils.isBlank(comment.getId()));

        String commentId = COMMENT_ID_PREFIX + UUID.randomUUID().toString();
        comment.setId(commentId);

        commentsMap.put(commentId, comment);
        commentsMapByS2Cell.put(comment.getL15S2CellToken(), comment);
        return comment;
    }

    @Override
    @Nullable
    public Comment load(String commentId) {
        return commentsMap.get(commentId);
    }

    @Override
    public List<Comment> loadByS2Cell(String s2cell) {

        checkArgument(StringUtils.isNotBlank(s2cell));
        return commentsMapByS2Cell.get(s2cell);
    }
}
