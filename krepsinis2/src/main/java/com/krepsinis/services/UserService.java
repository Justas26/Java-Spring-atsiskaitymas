package com.krepsinis.services;

import com.krepsinis.model.User;


public interface UserService {
    void save (User user);
    User findByUsername(String username);
    void update(User user);
    User findById(int id);

}
