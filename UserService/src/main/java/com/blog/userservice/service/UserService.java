package com.blog.userservice.service;

import com.blog.userservice.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> getAll();

    public Optional<User> get(Long id);

    public User add(User user);

    public void delete(Long id);

    public User update(User user, Long id);


}
