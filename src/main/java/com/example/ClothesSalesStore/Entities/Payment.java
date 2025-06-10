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
@Table(name = "Payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PaymentID")
    private int Id;
    @Column(name="Amount", nullable = false)
    private double Amount;
    @Column(name="PaymentDate", nullable = false)
    private LocalDateTime PaymentDate; // ISO 8601 format or any preferred format
    @Column(name="PaymentStatus", length = 50, nullable = false)
    private String PaymentStatus;

    //Relationship with Payment
    @OneToOne
    @JoinColumn(name = "OrderID")
    private Order order;
}
