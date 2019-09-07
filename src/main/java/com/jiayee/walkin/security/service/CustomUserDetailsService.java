package com.jiayee.walkin.security.service;

import com.jiayee.walkin.model.User;
import com.jiayee.walkin.repository.UserRepository;
import com.jiayee.walkin.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  // Called by Spring Security
  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsernameOrEmail(username, username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found. Username: " + username));
    return CustomUserDetails.create(user);
  }

  // Called by custom JwtAuthenticationFilter
  @Transactional
  public UserDetails loadUserById(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new UsernameNotFoundException("User not found. User ID: " + id));
    return CustomUserDetails.create(user);
  }

}
