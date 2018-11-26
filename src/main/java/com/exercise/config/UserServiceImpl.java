package com.exercise.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.exercise.model.Role;
import com.exercise.services.UserService;

@Service
public class UserServiceImpl implements UserDetailsService {

  @Autowired
  private UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    com.exercise.model.User user = userService.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User : " + username + " not found");
    }
    final List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
    for (final Role role : user.getRoles()) {
      grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
    }

    return new org.springframework.security.core.userdetails.User(user.getUsername(),
        user.getPassword(), grantedAuthorities);
  }

}
