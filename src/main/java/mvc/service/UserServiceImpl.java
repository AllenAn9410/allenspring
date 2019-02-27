package mvc.service;

import mvc.dao.UserDao;
import mvc.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public UserInfo login(String name, String pwd) {
        UserInfo userInfo = userDao.findByName(name);
        if (pwd.equalsIgnoreCase(userInfo.getPassword())) {
            return userInfo;
        } else {
            return new UserInfo();
        }
    }
}
