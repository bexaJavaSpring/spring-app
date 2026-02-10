package org.example.controller;

import org.example.dao.UserDao;
import org.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {
    @Autowired
    private UserDao userDao;

    @GetMapping
    public List<User> getAll() {
        return userDao.getAll();
    }

    @PostMapping
    public String save(@RequestBody User user) {
        userDao.save(user);
        return "User saved";
    }
}
