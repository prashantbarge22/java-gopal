package com.prashantbarge.learning2.service;

import com.prashantbarge.learning2.models.User;
import com.prashantbarge.learning2.repositry.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User save(User user){
     return user;
    }

}
