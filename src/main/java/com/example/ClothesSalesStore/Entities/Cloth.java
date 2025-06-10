package com.example.ClothesSalesStore.Entities;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Table(name = "clothes")
public class Cloth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ClothID")
    private int Id;

    @Column(name="ClothName", nullable = false, length = 100)
    private String Name;

    @Column(name="BriefDescription", length = 255)
    private String BriefDescription;

    @Column(name="FullDescription", length = 1000)
    private String FullDescription;

    @Column(name="TechnicalDescription", length = 1000)
    private String TechnicalDescription;

    @Column(name="Price", nullable = false)
    private double Price;

    @Column(name="ImageUrl", length = 255)
    private String ImageUrl;

    // Relationship with Cloth
    @ManyToOne
    @JoinColumn(name = "CategoryID")
    private Category category;

    @OneToMany(mappedBy = "cloth")
    private List<CartItem> cartItems = new ArrayList<>();


}
