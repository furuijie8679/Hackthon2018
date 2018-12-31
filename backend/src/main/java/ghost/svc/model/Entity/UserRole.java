package ghost.svc.model.Entity;

import org.hibernate.annotations.Tables;

import javax.persistence.*;

@Entity
@Table(indexes = {
        @Index(name = "userIdIndex", columnList = "userId", unique = false)
})
public class UserRole {

    private Long userId;
    private Long roleId;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
