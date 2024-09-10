package com.example.authenticationservice.service;

import com.example.authenticationservice.config.jwt.JwtProvider;
import com.example.authenticationservice.dto.UserDTO;
import com.example.authenticationservice.entity.Role;
import com.example.authenticationservice.entity.User;
import com.example.authenticationservice.repository.RoleRepository;
import com.example.authenticationservice.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder,
                       @Lazy AuthenticationManager authenticationManager,
                       JwtProvider jwtProvider) {
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

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id: " + id)
        );
    }

    public boolean verify(String username, String password) {
        return userRepository.findByEmail(username)
                .map((u)-> passwordEncoder.matches(password, u.getPassword()))
                .orElseThrow(() -> new BadCredentialsException("Invalid email or password"));
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = User.builder()
                .firstname(userDTO.getFirstname())
                .lastname(userDTO.getLastname())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            if ( user.getRoles() != null) {
                List<Role> roles = user.getRoles().stream()
                        .map(r -> roleRepository.findByRole(r.getRole())
                                .orElse(r)).toList();
                user.setRoles(roles);
            } else {
                user.setRoles(List.of(roleRepository.findByRole("USER").get()));
            }
            userRepository.save(user);
            UserDTO registeredUser = new UserDTO();
            registeredUser.setEmail(user.getEmail());
            registeredUser.setFirstname(user.getFirstname());
            registeredUser.setLastname(user.getLastname());
            registeredUser.setId(user.getId());
//            return userRepository.save(user);
            return registeredUser;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            throw new BadCredentialsException("Invalid email or password");
        }
    }

    public String generateToken(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtProvider.generateToken(authentication);
    }


}
