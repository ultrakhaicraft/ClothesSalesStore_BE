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
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="OrderId")
    private int id;
    @Column(name="PaymentMethod", nullable = false,length = 50)
    private String PaymentMethod;
    @Column(name="BillingAddress", nullable = false,length = 225)
    private String BillingAddress;
    @Column(name="OrderStatus", nullable = false,length = 50)
    private String OrderStatus;
    @Column(name="OrderDate", nullable = false)
    private LocalDateTime OrderDate; // ISO 8601 format or any preferred format


    private int CartId;
    private int UserId;
}
