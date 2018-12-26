package ghost.svc;

import ghost.svc.db.CommentsDao;
import ghost.svc.db.InMemoryCommentsDao;
import ghost.svc.model.Comment;
import ghost.svc.model.PostCommentRequest;
import ghost.svc.model.ex.CommentNotFoundException;
import ghost.svc.utils.GeoUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {

    private CommentsDao commentsDao = new InMemoryCommentsDao();

    /**
     * When client make comments.
     * Returns a comment ID.
     */
    @PostMapping("/comments/post")
    public Comment makeComment(@RequestBody final PostCommentRequest request) {
        Comment toSave = new Comment();
        toSave.setContent(request.getContent());
        toSave.setCreatorClientUuid(request.getClientUuid());
        toSave.setLat(request.getLat());
        toSave.setLng(request.getLng());
        toSave.setTimestamp(System.currentTimeMillis());
        toSave.setL15S2CellToken(GeoUtils.s2CellToken(request.getLat(), request.getLng()));

        Comment toReturn = commentsDao.save(toSave);
        return toReturn;
    }

    @GetMapping("/comments/query")
    public List<Comment> viewComments(@RequestParam("lat") final double lat,
                                      @RequestParam("lng") final double lng,
                                      @RequestParam("limit") final int limit,
                                      @RequestParam("pageToken") final String pageToken) {
        String s2cellToken = GeoUtils.s2CellToken(lat, lng);
        return commentsDao.loadByS2Cell(s2cellToken);
    }

    @GetMapping("/comments/get/{id}")
    public Comment viewCommentById(@PathVariable String id) {
        Comment toReturn = commentsDao.load(id);
        if (toReturn == null) {
            throw new CommentNotFoundException("cannot find comment by id " + id);
        }
        return toReturn;
    }
}
