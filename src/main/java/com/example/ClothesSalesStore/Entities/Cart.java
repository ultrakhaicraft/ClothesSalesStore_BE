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

    private boolean isActive; // Add this to enforce only one active cart in your service logic

    //Relationship with Cart
    @ManyToOne
    @JoinColumn(name="UserID") //watch out
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();

    @OneToOne(mappedBy = "cart", cascade = CascadeType.ALL)
    private Order order;

}
