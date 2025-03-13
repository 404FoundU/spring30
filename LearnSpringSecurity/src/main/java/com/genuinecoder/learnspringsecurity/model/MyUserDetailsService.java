package com.genuinecoder.learnspringsecurity.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    MyUserRepositoy myUserRepositoy;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //        return null;
        Optional<MyUser> myUserOptional = myUserRepositoy.findByUsername(username);
        if (myUserOptional.isPresent()) {
            MyUser myUser = myUserOptional.get();
           return User.withUsername(myUser.getUsername())
                    .password(myUser.getPassword())
                    .roles(getRoles(myUser))
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    private String[] getRoles(MyUser user) {
        if (user.getRole() == null) {
            return null;
        }
        return user.getRole().split(",");
    }

    public MyUser addUser(MyUser myUser) {
       return myUserRepositoy.save(myUser);
    }

}
