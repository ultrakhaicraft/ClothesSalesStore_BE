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
@Table(name = "ChatMessage")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "ChatMessageId")
    private int Id;

    @Column(name="Message", length = 1000)
    private String Message;

    @Column(name="SentAt", nullable = false)
    private LocalDateTime SentAt; // ISO 8601 format or any preferred format


    private int UserId; // ID of the user who sent the message
}
