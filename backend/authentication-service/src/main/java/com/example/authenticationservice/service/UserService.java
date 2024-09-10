package com.example.authenticationservice.service;

import com.example.authenticationservice.config.jwt.JwtProvider;
import com.example.authenticationservice.repository.RoleRepository;
import com.example.authenticationservice.repository.UserRepository;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found with email: " + username)
        );
    }

    public boolean verify(String username, String password) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public User createUser(User user) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public String generateToken(String email, String password) {
//        Authentication authentication;
        throw new UnsupportedOperationException("Not implemented yet");

    }



}
