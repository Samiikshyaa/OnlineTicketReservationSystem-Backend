package com.infinite.onlineTicket.controller;

import com.infinite.onlineTicket.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: Samikshya Timalsina
 * createdDate: 3/13/2025
 **/
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwt = authorizationHeader.substring(7);
            jwtUtil.blacklistToken(jwt);  // Blacklist the token
            return new ResponseEntity<>("Logged out successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return "Logged in as: " + ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        return "No user logged in";
    }
}

