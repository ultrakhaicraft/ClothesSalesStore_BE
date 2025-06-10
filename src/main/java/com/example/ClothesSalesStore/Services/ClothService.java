package com.example.ClothesSalesStore.Services;

import com.example.ClothesSalesStore.DTOModels.ApiResponse;
import com.example.ClothesSalesStore.DTOModels.Clothes.*;
import com.example.ClothesSalesStore.Entities.Category;
import com.example.ClothesSalesStore.Entities.Cloth;
import com.example.ClothesSalesStore.Repositories.CategoryRepository;
import com.example.ClothesSalesStore.Repositories.ClothRepository;
import com.example.ClothesSalesStore.Services.Interface.IClothService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClothService implements IClothService {

    private final ClothRepository clothRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ClothService(ClothRepository clothRepository, CategoryRepository categoryRepository) {
        this.clothRepository = clothRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ApiResponse<ClothCRUDResponse> createCloth(ClothCreateRequest request) {
        try {
            // Validate required fields
            if (request.getName() == null || request.getName().trim().isEmpty()) {
                ClothCRUDResponse response = ClothCRUDResponse.builder()
                        .success(false)
                        .message("Cloth name is required")
                        .build();

                return ApiResponse.<ClothCRUDResponse>builder()
                        .statusCode("400")
                        .Data(response)
                        .message("Validation failed - Cloth name is required")
                        .build();
            }

            if (request.getPrice() < 0) {
                ClothCRUDResponse response = ClothCRUDResponse.builder()
                        .success(false)
                        .message("Price cannot be negative")
                        .build();

                return ApiResponse.<ClothCRUDResponse>builder()
                        .statusCode("400")
                        .Data(response)
                        .message("Validation failed")
                        .build();
            }

            // Find and validate category
            Optional<Category> categoryOpt = categoryRepository.findById(request.getCategoryId());
            if (categoryOpt.isEmpty()) {
                ClothCRUDResponse response = ClothCRUDResponse.builder()
                        .success(false)
                        .message("Category not found with ID: " + request.getCategoryId())
                        .build();

                return ApiResponse.<ClothCRUDResponse>builder()
                        .statusCode("404")
                        .Data(response)
                        .message("Category not found")
                        .build();
            }

            Category category = categoryOpt.get();


            Cloth cloth = Cloth.builder()
                    .Name(request.getName())
                    .BriefDescription(request.getBriefDescription())
                    .FullDescription(request.getFullDescription())
                    .TechnicalDescription(request.getTechnicalDescription())
                    .Price(request.getPrice())
                    .ImageUrl(request.getImageUrl())
                    .category(category)
                    .build();

            // Save the cloth
            Cloth savedCloth = clothRepository.save(cloth);

            ClothCRUDResponse response = ClothCRUDResponse.builder()
                    .success(true)
                    .message("Cloth created successfully")
                    .Id(savedCloth.getId())
                    .cloth(savedCloth)
                    .build();

            return ApiResponse.<ClothCRUDResponse>builder()
                    .statusCode("201") // 201 for successful creation
                    .Data(response)
                    .message("Cloth created successfully")
                    .build();

        } catch (Exception e) {
            ClothCRUDResponse response = ClothCRUDResponse.builder()
                    .success(false)
                    .message("Error creating cloth: " + e.getMessage())
                    .build();

            return ApiResponse.<ClothCRUDResponse>builder()
                    .statusCode("500") // 500 for internal server error
                    .Data(response)
                    .message("Internal server error")
                    .build();
        }
    }

    @Override
    public ApiResponse<ClothCRUDResponse> updateCloth(ClothUpdateRequest request, String id) {
        try{

            // Validate input
            if (id == null || id.trim().isEmpty()) {
                return ApiResponse.<ClothCRUDResponse>builder()
                        .statusCode("400")
                        .Data(null)
                        .message("Cloth ID is required")
                        .build();
            }


            int clothId;
            try {
                clothId = Integer.parseInt(id.trim());
            } catch (NumberFormatException e) {

                return ApiResponse.<ClothCRUDResponse>builder()
                        .statusCode("400")
                        .Data(null)
                        .message("Validation failed - Invalid cloth ID format. ID must be a number")
                        .build();
            }

            if (request.getPrice() < 0) {
                return ApiResponse.<ClothCRUDResponse>builder()
                        .statusCode("400")
                        .Data(null)
                        .message("Validation failed - Price cannot be negative")
                        .build();
            }

            // Find cloth by ID
            Optional<Cloth> cloth = clothRepository.findById(clothId);

            if (cloth.isEmpty()) {

                return ApiResponse.<ClothCRUDResponse>builder()
                        .statusCode("400")
                        .Data(null)
                        .message("Cloth not found with ID: " + clothId)
                        .build();

            }

            // Find and validate category
            Optional<Category> categoryOpt = categoryRepository.findById(request.getCategoryId());
            if (categoryOpt.isEmpty()) {
                return ApiResponse.<ClothCRUDResponse>builder()
                        .statusCode("404")
                        .Data(null)
                        .message("Category not found with ID: " + request.getCategoryId())
                        .build();
            }

            Category category = categoryOpt.get();

            Cloth clothToUpdated = cloth.get();

            clothToUpdated = Cloth.builder()
                    .Name(request.getName())
                    .BriefDescription(request.getBriefDescription())
                    .FullDescription(request.getFullDescription())
                    .TechnicalDescription(request.getTechnicalDescription())
                    .Price(request.getPrice())
                    .ImageUrl(request.getImageUrl())
                    .category(category)
                    .build();


            // Save the cloth
            Cloth savedCloth = clothRepository.save(clothToUpdated);

            ClothCRUDResponse response = ClothCRUDResponse.builder()
                    .success(true)
                    .message("Cloth updated successfully")
                    .Id(savedCloth.getId())
                    .cloth(savedCloth)
                    .build();

            return ApiResponse.<ClothCRUDResponse>builder()
                    .statusCode("201") // 201 for successful creation
                    .Data(response)
                    .message("Cloth updated successfully")
                    .build();

        }catch(Exception e){
            return ApiResponse.<ClothCRUDResponse>builder()
                    .statusCode("404")
                    .Data(null)
                    .message("Error updating clothes" + e.getMessage())
                    .build();
        }
    }

    @Override
    public ApiResponse<ClothCRUDResponse> deleteCloth(String id) {
        try{

            ClothDetailResponse response;
            // Validate input
            if (id == null || id.trim().isEmpty()) {
                return ApiResponse.<ClothCRUDResponse>builder()
                        .statusCode("400")
                        .Data(null)
                        .message("Cloth ID is required")
                        .build();
            }

            int clothId;
            try {
                clothId = Integer.parseInt(id.trim());
            } catch (NumberFormatException e) {

                return ApiResponse.<ClothCRUDResponse>builder()
                        .statusCode("400")
                        .Data(null)
                        .message("Validation failed - Invalid cloth ID format. ID must be a number")
                        .build();
            }

            Optional<Cloth> clothToDeleted = clothRepository.findById(clothId);

            if(clothToDeleted.isEmpty()){
                return ApiResponse.<ClothCRUDResponse>builder()
                        .statusCode("400")
                        .Data(null)
                        .message("Cloth not found with ID: " + clothId)
                        .build();
            }

            clothRepository.delete(clothToDeleted.get());

            return ApiResponse.<ClothCRUDResponse>builder()
                    .statusCode("200")
                    .Data(null)
                    .message("Operation success - Cloth deleted with ID: " + clothId)
                    .build();

        }catch(Exception e){
            return ApiResponse.<ClothCRUDResponse>builder()
                    .statusCode("404")
                    .Data(null)
                    .message("Error deleting clothes" + e.getMessage())
                    .build();
        }
    }

    @Override
    public ApiResponse<List<ClothViewResponse>> getAllCloth(ClothQueryRequest request) {
        try {

            List<Cloth> clothes= clothRepository.findAll();

            //Transform Cloth into ClothViewResponse

            List<ClothViewResponse> data = clothes.stream()
                    .map(cloth -> ClothViewResponse.builder()
                            .Id(cloth.getId())
                            .Name(cloth.getName())
                            .BriefDescription(cloth.getBriefDescription())
                            .FullDescription(cloth.getFullDescription())
                            .TechnicalDescription(cloth.getTechnicalDescription())
                            .Price(cloth.getPrice())
                            .ImageUrl(cloth.getImageUrl())
                            .categoryId(cloth.getCategory() != null ? cloth.getCategory().getId() : 0)
                            .categoryName(cloth.getCategory() != null ? cloth.getCategory().getName() : null)
                            .build())
                    .collect(Collectors.toList());


            return ApiResponse.<List<ClothViewResponse>>builder()
                    .statusCode("200")
                    .Data(data)
                    .message("Operation successfully - getting all Clothes done")
                    .build();

        } catch (Exception e) {

            return ApiResponse.<List<ClothViewResponse>>builder()
                    .statusCode("404")
                    .Data(null)
                    .message("Error retrieving all clothes " + e.getMessage())
                    .build();
        }
    }

    @Override
    public  ApiResponse<ClothDetailResponse> getClothDetail(String id) {
        try {
            ClothDetailResponse response;
            // Validate input
            if (id == null || id.trim().isEmpty()) {
                return ApiResponse.<ClothDetailResponse>builder()
                        .statusCode("400")
                        .Data(null)
                        .message("Validation failed - Cloth ID is required")
                        .build();
            }

            int clothId;
            try {
                clothId = Integer.parseInt(id.trim());
            } catch (NumberFormatException e) {

                return ApiResponse.<ClothDetailResponse>builder()
                        .statusCode("400")
                        .Data(null)
                        .message("Validation failed - Invalid cloth ID format. ID must be a number")
                        .build();
            }

            // Find cloth by ID
            Optional<Cloth> clothOpt = clothRepository.findById(clothId);
            if (clothOpt.isEmpty()) {

                return ApiResponse.<ClothDetailResponse>builder()
                        .statusCode("400")
                        .Data(null)
                        .message("Cloth not found with ID: " + clothId)
                        .build();

            }

            Cloth cloth = clothOpt.get();

            // Build response with cloth details
            ClothDetailResponse.ClothDetailResponseBuilder responseBuilder = ClothDetailResponse.builder()
                    .Id(cloth.getId())
                    .Name(cloth.getName())
                    .BriefDescription(cloth.getBriefDescription())
                    .FullDescription(cloth.getFullDescription())
                    .TechnicalDescription(cloth.getTechnicalDescription())
                    .Price(cloth.getPrice())
                    .ImageUrl(cloth.getImageUrl());

            // Add category information if available
            if (cloth.getCategory() != null) {
                responseBuilder
                        .categoryId(cloth.getCategory().getId())
                        .categoryName(cloth.getCategory().getName());
            }

            response= responseBuilder.build();

            return ApiResponse.<ClothDetailResponse>builder()
                    .statusCode("200")
                    .Data(response)
                    .message("Operation successfully - getting Cloth detail done")
                    .build();

        } catch (Exception e) {

            return ApiResponse.<ClothDetailResponse>builder()
                    .statusCode("404")
                    .Data(null)
                    .message("Error retrieving cloth details: " + e.getMessage())
                    .build();
        }
    }


}
