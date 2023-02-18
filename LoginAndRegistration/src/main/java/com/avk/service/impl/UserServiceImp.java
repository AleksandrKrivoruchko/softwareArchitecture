package com.avk.service.impl;

import com.avk.dao.UserDao;
import com.avk.dao.impl.UserDaoImpl;
import com.avk.entity.User;
import org.springframework.util.DigestUtils;

public class UserServiceImp implements com.avk.service.UserService {
    UserDao userDao = new UserDaoImpl();
    @Override
    public String login(User user) {
        User findUser = userDao.findByLogin(user.getLogin());
        if (findUser != null)
            if (DigestUtils.md5DigestAsHex(user.getPassword()
                    .getBytes()).equals(findUser.getPassword())) {
                return "life is beautiful " + "your Id: " + findUser.getId();
            }
        return "do not give up";
    }

    @Override
    public String registration(User user) {
        User findUser = userDao.findByLogin(user.getLogin());
        if (findUser == null) {
            userDao.save(user);
            return "life is beautiful";
        }
        return "this login is not available";
    }
}
