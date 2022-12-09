package com.example.springhibernategb.services;

import com.example.springhibernategb.DAO.UserRepository;
import com.example.springhibernategb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteById1(int id) {
        userRepository.deleteById(id);
    }

    public User showById(int id) {
        return userRepository.findById(id).orElseThrow();
    }
}
