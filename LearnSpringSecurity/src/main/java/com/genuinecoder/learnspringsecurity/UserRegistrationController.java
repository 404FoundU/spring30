package com.genuinecoder.learnspringsecurity;

import com.genuinecoder.learnspringsecurity.model.MyUser;
import com.genuinecoder.learnspringsecurity.model.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class UserRegistrationController {

    @Autowired
    MyUserDetailsService myUserDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/user")
    public ResponseEntity<MyUser> registerUser(@RequestBody MyUser myUser) {
        String password = passwordEncoder.encode(myUser.getPassword());
        myUser.setPassword(password);
        MyUser result = myUserDetailsService.addUser(myUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
