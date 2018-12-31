package Ghost.backend;

import ghost.svc.GhostApplication;
import ghost.svc.db.RoleRepository;
import ghost.svc.db.UserRoleRepository;
import ghost.svc.model.Entity.User;
import ghost.svc.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GhostApplication.class)
public class UsersTests {

    @Autowired
    UserService userService;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRoleRepository userRoleRepository;

    @Test
    public void creat() {
        User user = new User();
        user.setUserName("UserTest20");
        user.setPassWord("pass123");
        user.setBirthDay(new Date());
        user.setPhoneNum("123123123");
        user.setEmail("123@gmail.com");
        userService.createUser(user);
    }

    @Test
    public void load() {
        User user = userService.findUserByUserName("UserTest01");
    }
}
