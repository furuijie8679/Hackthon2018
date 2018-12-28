package ghost.svc;

import ghost.svc.db.CommentRepository;
import ghost.svc.db.CommentsDao;
import ghost.svc.db.InMemoryCommentsDao;
import ghost.svc.model.Comment;
import ghost.svc.model.PostCommentRequest;
import ghost.svc.model.ex.CommentNotFoundException;
import ghost.svc.service.CommentsQueryService;
import ghost.svc.utils.GeoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CommentController {

    @Autowired
    CommentsQueryService commentsQueryService;

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
        toSave.setCellToken(GeoUtils.s2CellToken(request.getLat(), request.getLng()));
        Comment toReturn = commentsQueryService.makeComment(toSave);
        return toReturn;
    }


    /**
     * @param lat latitude
     * @param lng longitude
     * @param limit pageSize
     * @param pageToken pageIndex from zero
     * @return list of comments
     */
    @GetMapping("/comments/query")
    public List<Comment> viewComments(@RequestParam("lat") final double lat,
                                      @RequestParam("lng") final double lng,
                                      @RequestParam("limit") final int limit,
                                      @RequestParam("pageToken") final int pageToken) {
        return commentsQueryService.viewComments(lat, lng, limit, pageToken);
    }

    @GetMapping("/comments/get/{id}")
    public Comment viewCommentById(@PathVariable Integer id, int limit, String pageToken) {
        return commentsQueryService.viewCommentById(id, limit, pageToken);
    }
}
