package ghost.svc.service.impl;

import ghost.svc.db.RoleRepository;
import ghost.svc.db.UserRepository;
import ghost.svc.db.UserRoleRepository;
import ghost.svc.model.Entity.Role;
import ghost.svc.model.Entity.User;
import ghost.svc.model.Entity.UserRole;
import ghost.svc.model.ex.UserException;
import ghost.svc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public User createUser(User user) {
        if (!checkUserUniqle(user)) {
            throw new UserException("User exists already!");
        }
        /*SecureRandom secureRandom = new SecureRandom();
        byte[] value = new byte[20];
        secureRandom.nextBytes(value);
        String saltValue = Arrays.toString(value);
        user.setSaltValue(saltValue);*/
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodePw = bCryptPasswordEncoder.encode(user.getPassWord());
        user.setPassWord(encodePw);
        User newUser = userRepository.save(user);
        Role role = roleRepository.findByRole("User");
        UserRole userRole = new UserRole();
        userRole.setRoleId(role.getId());
        userRole.setUserId(newUser.getId());
        UserRole newUserRole = userRoleRepository.save(userRole);
        logger.info("User Id: " + newUserRole.getUserId() + "Role Id: " + userRole.getRoleId());
        return newUser;
    }

    @Override
    public User findUserByUserName(String userName, String pwd) {
        logger.info("========= UserName: " + userName + "============");
        Optional<User> result = userRepository.findByUserName(userName);
        if (!result.isPresent()) {
            throw new UserException("User does not exist!");
        }

        User user = result.get();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (!bCryptPasswordEncoder.matches(pwd + user.getSaltValue(), user.getPassWord())) {
            throw new UserException("User name or password is wrong!");
        }
        return user;
    }

    @Override
    public User findUserByUserName(String userName) {
        logger.info("========= UserName: " + userName + "============");
        Optional<User> result = userRepository.findByUserName(userName);
        if (!result.isPresent()) {
            throw new UserException("User does not exist!");
        }

        User user = result.get();
        return user;
    }

    private boolean checkUserUniqle(User user) {
        if (user == null)
            return false;
        Optional<User> oldUser = userRepository.findByUserName(user.getUserName());
        if (oldUser.isPresent() && user.getUserName().equals(oldUser.get().getUserName())) {
            return false;
        }
        return true;
    }
}
