package org.kata.tim.newMVC.services;


import org.kata.tim.newMVC.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(int id);

    void save(User user);

    void update(int id, User newUser);

    void delete(int id);

}
