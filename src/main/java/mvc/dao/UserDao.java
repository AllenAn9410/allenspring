package mvc.dao;

import mvc.entity.UserInfo;

public interface UserDao {
    UserInfo findByName(String name);
}
