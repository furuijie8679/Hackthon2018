package ghost.svc.service.impl;

import ghost.svc.db.CommentRepository;
import ghost.svc.model.Entity.Comment;
import ghost.svc.model.ex.CommentNotFoundException;
import ghost.svc.service.CommentsQueryService;
import ghost.svc.utils.GeoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentsQueryServiceImpl implements CommentsQueryService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment makeComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> viewComments(double lat, double lng, int limit, int pageToken) {
        String s2cellToken = GeoUtils.s2CellToken(lat, lng);
        Pageable pageable = PageRequest.of(pageToken, limit);
        Page<Comment> result = commentRepository.findByCellToken(pageable ,s2cellToken);
        List<Comment> list = new ArrayList<>();
        for(Comment comment : result){
            list.add(comment);
        }
        return list;
    }

    @Override
    public Comment viewCommentById(Integer id, int limit, String pageToken) {
        Optional<Comment> toReturn = commentRepository.findById(id);
        if (!toReturn.isPresent()) {
            throw new CommentNotFoundException("cannot find comment by id " + id);
        }
        return toReturn.get();
    }
}
