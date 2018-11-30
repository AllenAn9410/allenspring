package mvc.dao;

import mvc.entity.UserInfo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("userDao")
public class MysqlUserDao implements UserDao {
    @Resource
    private MysqlUserDao mysqlUserDao;

    @Override
    public UserInfo findByName(String name) {
        return mysqlUserDao.findByName(name);
    }
}
