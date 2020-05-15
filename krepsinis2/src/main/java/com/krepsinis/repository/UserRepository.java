package com.krepsinis.repository;

import com.krepsinis.model.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findUserById(int id);

}
