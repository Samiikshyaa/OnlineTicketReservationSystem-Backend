package com.infinite.onlineTicket.model;

import com.infinite.onlineTicket.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private Long userId;

    @Column(name ="user_name", unique = true)
    private String userName;

    @Column(name ="password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
