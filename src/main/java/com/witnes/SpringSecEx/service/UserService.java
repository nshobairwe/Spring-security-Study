package com.witnes.SpringSecEx.service;

import com.witnes.SpringSecEx.model.Users;
import com.witnes.SpringSecEx.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;


    public Users register(Users user){
       return repo.save(user);
    }
}
