package com.infinite.onlineTicket.entities;

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

    @Column(name ="user_name")
    private String userName;

    @Column(name ="password")
    private String password;

    @Column(name ="roles")
    private List<String> roles;
}
