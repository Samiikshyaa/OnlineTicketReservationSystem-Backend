package com.infinite.onlineTicket.service.user;

import com.infinite.onlineTicket.model.User;
import com.infinite.onlineTicket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public boolean saveUser(User user) {
        try {
            user.setUserName(user.getUserName());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(user.getRole());
            userRepository.save(user);
            return true;
        }catch (Exception ex) {
            return false;
        }
    }
}
