package org.example;

import com.google.gson.Gson;
import org.example.dao.OrderDao;
import org.example.dao.UserDao;
import org.example.models.Order;
import org.example.models.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // ========= 1. TABLE CREATE =========
//        createTables();

        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        // bean olish
        UserDao userDao = (UserDao) context.getBean("userDao");
        OrderDao orderDao = (OrderDao) context.getBean("orderDao");


        System.out.println(new Gson().toJson(userDao.getById(1L)));

        // ========== USER SAVE ==========
//        User user = new User();
//        user.setName("Alisher");
//        userDao.save(user);
//
//        // DB dan user olish
//        List<User> users = userDao.getAll();
//        User dbUser = users.get(1);
//
//        // ========== ORDER SAVE ==========
//        Order order = new Order();
//        order.setProduct("Naushnik");
//        order.setUser(dbUser);
//        orderDao.save(order);

        System.out.println("Successfully inserted data");
    }

    public static void createTables() {

        String url = "jdbc:postgresql://localhost:5432/spring_app";
        String username = "postgres";
        String password = "bexa";

        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();

            // users table
            stmt.executeUpdate("""
                        CREATE TABLE IF NOT EXISTS users(
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(100)
                        )
                    """);

            // orders table
            stmt.executeUpdate("""
                        CREATE TABLE IF NOT EXISTS orders(
                            id SERIAL PRIMARY KEY,
                            product VARCHAR(100),
                            user_id INT REFERENCES users(id)
                        )
                    """);

            System.out.println("Tables ready");

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}