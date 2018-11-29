package mvc.dao;

import mvc.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class MysqlUserDao implements UserDao {

    @Override
    public UserInfo findByName(String name) {
        UserInfo userInfo = new UserInfo();
        userInfo.setKey(1);
        userInfo.setName("a");
        userInfo.setPassword("a");
        return userInfo;
    }
}
