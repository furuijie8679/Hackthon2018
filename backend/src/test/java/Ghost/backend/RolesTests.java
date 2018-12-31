package Ghost.backend;

import ghost.svc.GhostApplication;
import ghost.svc.db.RoleRepository;
import ghost.svc.db.UserRoleRepository;
import ghost.svc.model.Entity.Role;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GhostApplication.class)
public class RolesTests {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRoleRepository userRoleRepository;
    @Test
    public void createRole() {
        Role role = new Role();
        role.setRole("User");
        roleRepository.save(role);
        Role role2 = new Role();
        role2.setRole("PremUser");
        roleRepository.save(role2);
    }
}
