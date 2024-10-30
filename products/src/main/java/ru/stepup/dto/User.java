package ru.stepup.dto;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "Username")
    private String username;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }
}
