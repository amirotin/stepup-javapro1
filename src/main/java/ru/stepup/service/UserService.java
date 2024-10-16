package ru.stepup.service;

import org.springframework.stereotype.Service;
import ru.stepup.dao.UserDao;
import ru.stepup.entity.User;
import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void createTable() {
        userDao.createTable();
    }

    public void dropTable() {
        userDao.dropTable();
    }

    public void save(User user) {
        userDao.save(user);
    }

    public User findByName(String name) {
        return userDao.findByName(name);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void delete(User user) {
        userDao.delete(user);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }
}
