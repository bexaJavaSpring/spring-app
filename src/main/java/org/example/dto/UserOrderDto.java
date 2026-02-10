package org.example.dto;

import java.util.ArrayList;
import java.util.List;

public class UserOrderDto {
    private Long userId;
    private String name;
    private List<OrderDto> orders = new ArrayList<>();

    public List<OrderDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDto> orders) {
        this.orders = orders;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
