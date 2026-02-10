package org.example;

import org.example.dao.OrderDao;
import org.example.dao.UserDao;
import org.example.models.Order;
import org.example.models.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = (UserDao) context.getBean("userDao");
        OrderDao orderDao = (OrderDao) context.getBean("orderDao");

        // user create
        User user = new User();
        user.setName("Bekhruz");
        userDao.save(user);

        // order create
        Order order = new Order();
        order.setProduct("Naushnik");
        order.setUser(user);
        orderDao.save(order);

        System.out.println("Successfully worked");
    }
}