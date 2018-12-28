package Ghost.backend;

import ghost.svc.GhostApplication;
import ghost.svc.db.CommentRepository;
import ghost.svc.model.Comment;
import ghost.svc.service.CommentsQueryService;
import ghost.svc.utils.GeoUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GhostApplication.class)
public class BackendApplicationTests {

	@Autowired
	CommentRepository commentRepository;
	@Autowired
	CommentsQueryService commentsQueryService;
	@Test
	public void save() {
		Comment toSave = new Comment();
		//toSave.setId(123456);
		toSave.setContent("comment content2!");
		toSave.setCreatorClientUuid("uuid-124");
		toSave.setLat(50.00);
		toSave.setLng(50.00);
		toSave.setTimestamp(System.currentTimeMillis());
		toSave.setCellToken(GeoUtils.s2CellToken(50.00, 50.00));

		Comment toReturn = commentsQueryService.makeComment(toSave);
	}

	@Test
	public void find() {
		Comment toSave = new Comment();
		//toSave.setId(123456);
		toSave.setContent("comment content2!");
		toSave.setCreatorClientUuid("uuid-124");
		toSave.setLat(50.00);
		toSave.setLng(50.00);
		toSave.setTimestamp(System.currentTimeMillis());
		toSave.setCellToken(GeoUtils.s2CellToken(50.00, 50.00));

		List<Comment> results = commentsQueryService.viewComments(50, 50, 10, 1);
		System.out.println(results.get(0).getCellToken());
	}

}

