package com.example.ClothesSalesStore.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="UserID")
    private int id;

    @Column(name="Username", unique = true, nullable = false, length = 50)
    private String username;

    @Column(name="PasswordHash", nullable = false)
    private String password;

    @Column(name="Email", unique = true, nullable = false, length = 100)
    private String email;

    @Column(name="PhoneNumber", unique = true, length = 15)
    private String phoneNumber;

    @Column(name="Address")
    private String address;

    @Column(name="Role", nullable = false, length = 50)
    private String role;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Cart> carts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Notification> notifications = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ChatMessage> messages = new ArrayList<>();
}
