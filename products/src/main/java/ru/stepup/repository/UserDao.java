package ru.stepup.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ru.stepup.dto.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createTable() {
        jdbcTemplate.execute("CREATE TABLE users (id SERIAL PRIMARY KEY, name VARCHAR(255))");
    }

    public void dropTable() {
        jdbcTemplate.execute("DROP TABLE users");
    }

    public void save(User user) {
        jdbcTemplate.update("INSERT INTO users (name) VALUES (?)", user.getUsername());
    }

    public User findByName(String name) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE name = ?", new UserMapper(), name);
    }

    public void update(User user) {
        jdbcTemplate.update("UPDATE users SET name = ? WHERE id = ?", user.getUsername(), user.getId());
    }

    public void delete(User user) {
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", user.getId());
    }

    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM users", new UserMapper());
    }

    public User findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?", new UserMapper(), id);
    }

    static class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("name"));
            return user;
        }
    }
}
