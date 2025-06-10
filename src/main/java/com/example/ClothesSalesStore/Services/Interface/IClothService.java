package com.example.ClothesSalesStore.Services.Interface;

import com.example.ClothesSalesStore.DTOModels.ApiResponse;
import com.example.ClothesSalesStore.DTOModels.Clothes.*;

import java.util.List;

public interface IClothService {
    ApiResponse<ClothCRUDResponse> createCloth(ClothCreateRequest request);
    ApiResponse<ClothCRUDResponse> updateCloth(ClothUpdateRequest request, String id);
    ApiResponse<ClothCRUDResponse> deleteCloth(String id);
    ApiResponse<List<ClothViewResponse>> getAllCloth(ClothQueryRequest request);
    ApiResponse<ClothDetailResponse> getClothDetail(String id);
}
