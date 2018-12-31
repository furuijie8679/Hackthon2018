package ghost.svc.model.Entity;

import javax.persistence.*;

@Entity
@Table(indexes = {
        @Index(name = "roleIndex", columnList = "role", unique = true)
})
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
