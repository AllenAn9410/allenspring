package mvc.service;

import mvc.entity.UserInfo;

public interface UserService {
    UserInfo login(String username, String password);

}
