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
@Table(name = "StoreLocations")
public class StoreLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="LocationId")
    private int Id;

    @Column(name="Latitude", nullable = false)
    private double Latitude; // Latitude of the store location

    @Column(name="Longitude", nullable = false)
    private double Longitude; // Longitude of the store location

    @Column(name="Address", length = 255, nullable = false)
    private String Address; // Full address of the store location
}
