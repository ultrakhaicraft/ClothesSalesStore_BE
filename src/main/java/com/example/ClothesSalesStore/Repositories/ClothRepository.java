package com.example.ClothesSalesStore.Repositories;

import com.example.ClothesSalesStore.Entities.Cloth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothRepository extends JpaRepository<Cloth, Integer> {
    //Cloth findByName(String name);
}
