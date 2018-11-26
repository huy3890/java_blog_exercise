package com.exercise.services.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.exercise.model.User;
import com.exercise.repository.UserRepository;
import com.exercise.services.UserService;

@Service
@Transactional
public class UserServiceJpaImpl implements UserService {

  @Override
  public boolean authenticate(String username, String password) {
    return false;
  }

  @Autowired
  private UserRepository userRepository;

  public PasswordEncoder passwordEncoder() {
    PasswordEncoder encoder = new BCryptPasswordEncoder();
    return encoder;
  }

  @Override
  public List<User> findAll() {
    return this.userRepository.findAll();
  }

  @Override
  public Optional<User> findById(Long id) {
    return this.userRepository.findById(id);
  }

  @Override
  public User create(User user) {
    if (user == null) {
      return null;
    }
    user.setPassword(passwordEncoder().encode(user.getPassword()));
    return this.userRepository.save(user);
  }

  @Override
  public User edit(User user) {
    return this.userRepository.save(user);
  }

  @Override
  public void deleteById(Long id) {
    this.userRepository.deleteById(id);
  }

  @Override
  public User findByUsername(String userName) {
    return this.userRepository.findByUsername(userName);
  }

}
