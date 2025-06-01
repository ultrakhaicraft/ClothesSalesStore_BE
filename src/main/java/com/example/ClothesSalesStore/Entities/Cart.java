package com.example.ClothesSalesStore.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CartID")
    private int Id;
    @Column(name="TotalPrice", nullable = false)
    private double TotalPrice;
    @Column(name="Status", nullable = false, length = 50)
    private String Status; // e.g., "Active", "Completed", "Cancelled"

    //Relationship with User
    private int UserId;

}
