package ghost.svc.db;

import ghost.svc.model.Entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    public List<UserRole> findByUserId(Long userId);
}
