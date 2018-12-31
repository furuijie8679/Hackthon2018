package ghost.svc.service;

import ghost.svc.model.Entity.User;

public interface UserService {

    public User createUser(User user);

    public User findUserByUserName(String userName, String pwd);

    public User findUserByUserName(String userName);
}
