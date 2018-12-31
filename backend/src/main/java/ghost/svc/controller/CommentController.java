package ghost.svc.controller;

import ghost.svc.model.Entity.Comment;
import ghost.svc.model.PostCommentRequest;
import ghost.svc.service.CommentsQueryService;
import ghost.svc.utils.GeoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentsQueryService commentsQueryService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * When client make comments.
     * Returns a comment ID.
     */
    @PostMapping("/comments/post")
    public Comment makeComment(@RequestBody final PostCommentRequest request) {
        logger.info("latitude: " + request.getLat() + ", " + "longtitude: " + request.getLng());
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
     * @param lat       latitude
     * @param lng       longitude
     * @param limit     pageSize
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
