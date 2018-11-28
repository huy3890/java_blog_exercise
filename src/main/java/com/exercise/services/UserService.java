package com.exercise.services;

import java.util.List;
import java.util.Optional;
import com.exercise.model.User;

public interface UserService {

  boolean authenticate(String username, String password);

  List<User> findAll();

  Optional<User> findById(Long id);

  User create(User user, List<String> role);

  User edit(User user);

  void deleteById(Long id);

  User findByUsername(String userName);
}
