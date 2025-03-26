package com.infinite.onlineTicket.controller;

import com.infinite.onlineTicket.dto.GlobalApiResponse;
import com.infinite.onlineTicket.dto.UserDto;
import com.infinite.onlineTicket.model.enums.Role;
import com.infinite.onlineTicket.service.UserService;
import com.infinite.onlineTicket.model.User;
import com.infinite.onlineTicket.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class UserController extends BaseController {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<GlobalApiResponse> signup(@RequestBody UserDto user) {
        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setPassword(user.getPassword());
        newUser.setRole(user.getRole().equalsIgnoreCase("admin") ? Role.ADMIN : Role.PASSENGER);
        userService.saveUser(newUser);
        return new ResponseEntity<>(successResponse("User registered successfully", newUser.getUserName()), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
            String role = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)  // Get the role from the authorities
                    .findFirst().orElse("PASSENGER");
            String jwt = jwtUtil.generateToken(userDetails.getUsername(), role);
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Incorrect username and password", HttpStatus.BAD_REQUEST);
        }
    }
}
