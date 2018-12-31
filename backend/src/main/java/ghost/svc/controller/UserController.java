package ghost.svc.controller;

import ghost.svc.model.CreateUserRequest;
import ghost.svc.model.Entity.User;
import ghost.svc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User createUser(@RequestBody CreateUserRequest request) {
        User user = new User();
        user.setUserName(request.getUserName());
        user.setPassWord(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPhoneNum(request.getPhoneNum());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setTimeZone(TimeZone.getTimeZone(request.getTimeZone()));
        format.setLenient(false);
        Date date = null;
        try {
            date = format.parse(request.getBirthDay());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setBirthDay(date);
        User newUser = userService.createUser(user);
        return newUser;
    }

    /*@RequestMapping(value = "/user", method = RequestMethod.GET)
    public User findUserByUserName(@RequestParam String userName, String pwd) {
        User user = userService.findUserByUserName(userName, pwd);
        return user;
    }*/
}
