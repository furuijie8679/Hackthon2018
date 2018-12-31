package ghost.svc.security;

import ghost.svc.db.RoleRepository;
import ghost.svc.db.UserRepository;
import ghost.svc.db.UserRoleRepository;
import ghost.svc.model.Entity.Role;
import ghost.svc.model.Entity.User;
import ghost.svc.model.Entity.UserRole;
import ghost.svc.model.ex.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class GhostUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> result = userRepository.findByUserName(username);
        if (!result.isPresent()) {
            throw new UserException("User does not exist!");
        }
        User user = result.get();
        List<UserRole> userRoles = userRoleRepository.findByUserId(user.getId());
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        for (UserRole userRole : userRoles) {
            Optional<Role> role = roleRepository.findById(userRole.getRoleId());
            if(role.isPresent())
                authorities.add(new SimpleGrantedAuthority(role.get().getRole()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassWord(), authorities);
    }
}
