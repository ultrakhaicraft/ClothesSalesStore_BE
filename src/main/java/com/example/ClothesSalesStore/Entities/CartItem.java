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
@Table(name = "CartItems")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CartItemID")
    private int id;
    @Column(name="Quantity", nullable = false)
    private int Quantity;
    @Column(name="Price", nullable = false)
    private double Price; // Price per item at the time of adding to cart

    // Relationship with CartItem
    private int CartId;
    private int ProductId; // This refers to the Cloth entity

}
