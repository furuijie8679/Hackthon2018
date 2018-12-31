package ghost.svc.service;

import ghost.svc.model.Entity.Comment;

import java.util.List;

public interface CommentsQueryService {

    public Comment makeComment(Comment comment);

    public List<Comment> viewComments(double lat,
                                      double lng,
                                      int limit,
                                      int pageToken);

    public Comment viewCommentById(Integer id, int limit, String pageToken);
}
