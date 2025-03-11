package com.infinite.onlineTicket.repository;

import com.infinite.onlineTicket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String username);
    void deleteByUserName(String username);
}
