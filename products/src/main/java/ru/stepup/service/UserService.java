package ru.stepup.service;

import org.springframework.stereotype.Service;
import ru.stepup.repository.UserDao;
import ru.stepup.dto.User;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User findById(Long id) {
        return userDao.findById(id);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }
}
