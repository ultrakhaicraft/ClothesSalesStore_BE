package com.example.ClothesSalesStore.DTOModels.Clothes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClothCreateRequest {
    private int Id;
    private String Name;
    private String BriefDescription;
    private String FullDescription;
    private String TechnicalDescription;
    private double Price;
    private String ImageUrl;
    private int categoryId;
}
