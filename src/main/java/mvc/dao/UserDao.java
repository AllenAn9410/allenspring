package mvc.dao;

import mvc.entity.UserInfo;

public interface UserDao {
    public UserInfo findByName(String name);
}
