package org.kata.tim.newMVC.services;


import org.kata.tim.newMVC.dao.UserDao;
import org.kata.tim.newMVC.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl
        implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> findAll() {
        return userDao.showAll();
    }
    @Override
    public User findById(int id) {
        Optional<User> optional = Optional.ofNullable(userDao.showById(id));
        return optional.orElse(null);
    }

    @Override
    public void save(User user) {
        userDao.create(user);
    }

    @Override
    public void update(int id, User newUser) {
        newUser.setId(id);
        userDao.update(id, newUser);
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }


}
