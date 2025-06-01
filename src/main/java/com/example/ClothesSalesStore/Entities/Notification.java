package com.example.ClothesSalesStore.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="OrderId")
    private int Id;
    @Column(name="Message", length = 255, nullable = false)
    private String Message;
    @Column(name="IsRead", nullable = false)
    private boolean IsRead; // Indicates if the notification has been read by the user

    @Column(name="CreatedAt", nullable = false)
    private LocalDateTime CreatedAt; // ISO 8601 format or any preferred format

    private int UserId; // ID of the user to whom the notification is sent
}
