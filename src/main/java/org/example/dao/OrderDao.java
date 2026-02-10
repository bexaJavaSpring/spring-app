package org.example.dao;

import org.example.models.Order;

import java.util.List;

public interface OrderDao {
    void save(Order order);
    Order getById(Long id);
    List<Order> getAll();
    void update(Order order);
    void delete(Long id);
}
