package com.example.ClothesSalesStore.DTOModels.Clothes;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClothUpdateRequest {

    public String Name;
    public String BriefDescription;
    public String FullDescription;
    public String TechnicalDescription;
    public double Price;
    public String ImageUrl;
    public int categoryId;
}
