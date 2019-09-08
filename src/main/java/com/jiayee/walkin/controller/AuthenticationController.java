package com.jiayee.walkin.controller;

import com.jiayee.walkin.exception.AppException;
import com.jiayee.walkin.model.Role;
import com.jiayee.walkin.model.Role.RoleName;
import com.jiayee.walkin.model.User;
import com.jiayee.walkin.payload.ApiResponse;
import com.jiayee.walkin.payload.JwtAuthenticationResponse;
import com.jiayee.walkin.payload.LoginRequest;
import com.jiayee.walkin.payload.RegisterRequest;
import com.jiayee.walkin.repository.RoleRepository;
import com.jiayee.walkin.repository.UserRepository;
import com.jiayee.walkin.security.JwtTokenProvider;
import java.net.URI;
import java.util.Collections;
import javax.validation.Valid;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

  @Setter(onMethod = @__({@Autowired}))
  AuthenticationManager authenticationManager;

  @Setter(onMethod = @__({@Autowired}))
  JwtTokenProvider tokenProvider;

  @Setter(onMethod = @__({@Autowired}))
  PasswordEncoder passwordEncoder;

  @Setter(onMethod = @__({@Autowired}))
  UserRepository userRepository;

  @Setter(onMethod = @__({@Autowired}))
  RoleRepository roleRepository;

  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            loginRequest.getUsernameOrEmail(),
            loginRequest.getPassword()
        )
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);
    return ResponseEntity
        .ok(new JwtAuthenticationResponse(tokenProvider.generateToken(authentication)));
  }

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(
      @Valid
      @RequestBody
          RegisterRequest registerRequest
  ) {
    if (userRepository.existsByUsername(registerRequest.getUsername())) {
      return new ResponseEntity<>(
          new ApiResponse(
              false,
              "Username already in use."
          ),
          HttpStatus.BAD_REQUEST
      );
    }
    if (userRepository.existsByEmail(registerRequest.getEmail())) {
      return new ResponseEntity<>(
          new ApiResponse(
              false,
              "Email address already in use."
          ),
          HttpStatus.BAD_REQUEST
      );
    }
    User user = new User(
        registerRequest.getFirstName(),
        registerRequest.getMiddleName(),
        registerRequest.getLastName(),
        registerRequest.getUsername(),
        registerRequest.getEmail(),
        registerRequest.getPassword()
    );
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    Role userRole = roleRepository
        .findByName(RoleName.ROLE_USER)
        .orElseThrow(() -> new AppException(
            "User role not set."
        ));
    user.setRoles(Collections.singleton(userRole));
    User savedUser = userRepository.save(user);
    URI location = ServletUriComponentsBuilder
        .fromCurrentContextPath()
        .path("/api/users/{username}")
        .buildAndExpand(savedUser.getUsername()).toUri();
    return ResponseEntity
        .created(location)
        .body(new ApiResponse(
            true,
            "User registered successfully."
        ));
  }

}
