package org.example.dao;

import org.example.dto.UserOrderDto;
import org.example.models.User;

import java.util.List;

public interface UserDao {
    void save(User user);
    UserOrderDto getById(Long id);
    List<User> getAll();
    void update(User user);
    void delete(Long id);
}
