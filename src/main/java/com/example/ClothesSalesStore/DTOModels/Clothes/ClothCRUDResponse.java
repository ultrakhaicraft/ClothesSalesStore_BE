package com.example.ClothesSalesStore.DTOModels.Clothes;

import com.example.ClothesSalesStore.Entities.Cloth;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClothCRUDResponse {
    private int Id;
    private String message;
    private boolean success;
    private Cloth cloth;
}
