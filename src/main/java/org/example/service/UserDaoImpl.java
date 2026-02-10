package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dao.UserDao;
import org.example.dto.OrderDto;
import org.example.dto.UserOrderDto;
import org.example.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();


    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(User user) {
        String sql = "insert into users(name) values(?)";
        jdbcTemplate.update(sql, user.getName());
    }

    public UserOrderDto getById(Long id) {
        String sql = "select u.id, u.name, " +
                "       json_agg( " +
                "        json_build_object( " +
                "                'id', o.id, " +
                "                'product', o.product " +
                "        )) AS orders " +
                "from users u " +
                "         inner join orders o " +
                "on o.user_id = u.id " +
                "where u.id=? " +
                "group by u.id ";
        return jdbcTemplate.queryForObject(sql, userOrderRowMapper, id);
    }

    public List<User> getAll() {
        String sql = "select * from users";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    public void update(User user) {
        String sql = "update users set name=? where id=?";
        jdbcTemplate.update(sql, user.getName(), user.getId());
    }

    public void delete(Long id) {
        String sql = "delete from users where id=?";
        jdbcTemplate.update(sql, id);
    }


    private RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User u = new User();
        u.setId(rs.getLong("id"));
        u.setName(rs.getString("name"));
        return u;
    };

    private RowMapper<UserOrderDto> userOrderRowMapper = (rs, rowNum) -> {
        UserOrderDto dto = new UserOrderDto();
        dto.setUserId(rs.getLong("id"));
        dto.setName(rs.getString("name"));
        List<OrderDto> orders = new ArrayList<>();
        String ordersJson = rs.getString("orders");
        if (ordersJson != null) {
            try {
                List<OrderDto> orderDtos = objectMapper.readValue(
                        ordersJson,
                        new TypeReference<>() {
                        }
                );
                dto.setOrders(orderDtos);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            dto.setOrders(Collections.emptyList());
        }
        dto.setOrders(orders);
        return dto;
    };
}
