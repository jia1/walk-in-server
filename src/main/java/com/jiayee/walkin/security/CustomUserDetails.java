package com.jiayee.walkin.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jiayee.walkin.model.User;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@AllArgsConstructor
@Value
public class CustomUserDetails implements UserDetails {

  private Long id;

  private String name;

  private String username;

  @JsonIgnore
  private String email;

  @JsonIgnore
  private String password;

  private Collection<? extends GrantedAuthority> authorities;

  public static CustomUserDetails create(User user) {
    List<GrantedAuthority> authorities = user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
        .collect(Collectors.toList());
    return new CustomUserDetails(
        user.getId(),
        user.getName(),
        user.getUsername(),
        user.getEmail(),
        user.getPassword(),
        authorities
    );
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
