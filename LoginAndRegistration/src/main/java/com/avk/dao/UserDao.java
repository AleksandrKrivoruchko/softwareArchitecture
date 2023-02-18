package com.avk.dao;

import com.avk.entity.User;

public interface UserDao {
    User findByLogin(String login);
    Boolean save(User user);
}
