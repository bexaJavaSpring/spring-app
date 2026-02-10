package org.example.service;

import org.example.dao.OrderDao;
import org.example.models.Order;
import org.example.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Order order){
        String sql = "insert into orders(product, user_id) values(?,?)";
        jdbcTemplate.update(sql,
                order.getProduct(),
                order.getUser().getId());
    }

    @Override
    public Order getById(Long id) {
        return null;
    }

    public List<Order> getAll(){
        String sql = """
            select o.id,o.product,u.id as user_id,u.name
            from orders o
            join users u on o.user_id=u.id
            """;
        return jdbcTemplate.query(sql, orderMapper);
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void delete(Long id) {

    }

    private RowMapper<Order> orderMapper = (rs, rowNum) -> {
        User u = new User();
        u.setId(rs.getLong("user_id"));
        u.setName(rs.getString("name"));

        Order o = new Order();
        o.setId(rs.getLong("id"));
        o.setProduct(rs.getString("product"));
        o.setUser(u);

        return o;
    };
}
