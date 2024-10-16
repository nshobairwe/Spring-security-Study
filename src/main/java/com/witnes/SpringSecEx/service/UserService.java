package com.witnes.SpringSecEx.service;

import com.witnes.SpringSecEx.model.Users;
import com.witnes.SpringSecEx.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public Users register(Users user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public String verify(Users user) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));

       if( authentication.isAuthenticated())
            return jwtService.generateToken(user.getUsername());

       return "fail";
    }
}
