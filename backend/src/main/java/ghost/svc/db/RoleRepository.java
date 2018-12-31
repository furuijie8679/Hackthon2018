package ghost.svc.db;

import ghost.svc.model.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    public Role findByRole(String role);
}
