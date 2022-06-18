package com.blog.userservice.service;

import com.blog.userservice.domain.User;
import com.blog.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserRepository userRepository;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> get(Long id){
        return userRepository.findById(id);
    }

    public User add(User user){
        return userRepository.save(user);
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }

    public User update(User user, Long id){
        User userFound = userRepository.findById(id).get();
        if(userFound != null){
            userFound = user;
            userFound.setId(id);
            userRepository.save(userFound);
        }
        return userRepository.save(user);
    }
}
