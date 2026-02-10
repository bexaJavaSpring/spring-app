package org.example.service;

import org.example.dao.UserDao;
import org.example.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(User user){
        String sql = "insert into users(name) values(?)";
        jdbcTemplate.update(sql, user.getName());
    }

    public User getById(Long id){
        String sql = "select * from users where id=?";
        return jdbcTemplate.queryForObject(sql, userRowMapper, id);
    }

    public List<User> getAll(){
        String sql = "select * from users";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    public void update(User user){
        String sql = "update users set name=? where id=?";
        jdbcTemplate.update(sql, user.getName(), user.getId());
    }

    public void delete(Long id){
        String sql = "delete from users where id=?";
        jdbcTemplate.update(sql, id);
    }

    private RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User u = new User();
        u.setId(rs.getLong("id"));
        u.setName(rs.getString("name"));
        return u;
    };
}
