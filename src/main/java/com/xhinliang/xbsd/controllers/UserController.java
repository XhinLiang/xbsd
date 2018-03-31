package com.xhinliang.xbsd.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xhinliang.xbsd.db.model.User;
import com.xhinliang.xbsd.db.repository.UserRepository;

/**
 * @author xhinliang
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/register")
    public User register(String email, String nickname, String password, long birthTime,
                         int birthdayType) {
        User user = userRepository
                .save(new User(email, nickname, password, birthTime, birthdayType));
        return user;
    }

    @RequestMapping("/login")
    public List<User> sort() {
        List<User> user = userRepository.findAll(new Sort(Sort.Direction.ASC, "age"));
        return user;
    }
}
