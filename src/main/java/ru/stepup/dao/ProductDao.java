package ru.stepup.dao;

import ru.stepup.entity.Product;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.stepup.entity.ProductType;
import ru.stepup.entity.User;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class ProductDao {
    private final JdbcTemplate jdbcTemplate;

    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createTable() {
        jdbcTemplate.execute("CREATE TABLE products (id SERIAL PRIMARY KEY, account VARCHAR(255), balance DECIMAL(10, 2), product_type VARCHAR(255), user_id BIGINT)");
    }

    public void dropTable() {
        jdbcTemplate.execute("DROP TABLE products");
    }

    public Product save(Product product) {
        jdbcTemplate.update("INSERT INTO products (account, balance, product_type, user_id) VALUES (?, ?, ?, ?)", product.getAccount(), product.getBalance(), product.getProductType(), product.getUserId());
        return product;
    }

    public Product findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM products WHERE id = ?", new ProductMapper(), id);
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM products WHERE id = ?", id);
    }

    public List<Product> findByUserId(Long userId) {
        return jdbcTemplate.query("SELECT * FROM products WHERE user_id = ?", new ProductMapper(), userId);
    }

    class ProductMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
            product.setId(rs.getLong("id"));
            product.setAccount(rs.getString("account"));
            product.setBalance(rs.getBigDecimal("balance"));
            product.setProductType(ProductType.getByCode(rs.getInt("product_type")));
            UserDao userDao = new UserDao(jdbcTemplate);
            User user = userDao.findById(rs.getLong("user_id"));
            product.setUserId(user);
            return product;
        }
    }
}
