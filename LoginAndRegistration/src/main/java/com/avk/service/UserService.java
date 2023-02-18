package com.avk.service;

import com.avk.entity.User;

public interface UserService {
    String login(User user);
    String registration(User user);
}
