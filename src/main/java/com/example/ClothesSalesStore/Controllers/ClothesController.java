package com.example.ClothesSalesStore.Controllers;

import com.example.ClothesSalesStore.DTOModels.ApiResponse;
import com.example.ClothesSalesStore.DTOModels.Clothes.*;
import com.example.ClothesSalesStore.Services.ClothService;
import com.example.ClothesSalesStore.Services.Interface.IClothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clothes")
public class ClothesController {
    private final IClothService clothService;

    @Autowired
    public ClothesController(IClothService clothService) {
        this.clothService = clothService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ClothCRUDResponse>> createCloth(@RequestBody ClothCreateRequest request) {
        ApiResponse<ClothCRUDResponse> response = clothService.createCloth(request);

        if (response.statusCode.equals("200")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping
    public ResponseEntity<ApiResponse<ClothCRUDResponse>> updateCloth(@RequestBody ClothUpdateRequest request,
        String id){
        ApiResponse<ClothCRUDResponse> response = clothService.updateCloth(request,id);

        if (response.statusCode.equals("200")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<ClothCRUDResponse>> deleteCloth(String id){
        ApiResponse<ClothCRUDResponse> response = clothService.deleteCloth(id);

        if (response.statusCode.equals("200")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<ClothViewResponse>>> getAllClothes(@RequestBody ClothQueryRequest request){
        ApiResponse<List<ClothViewResponse>> response = clothService.getAllCloth(request);

        if (response.statusCode.equals("200")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/detail")
    public ResponseEntity<ApiResponse<ClothDetailResponse>> getClothDetail(String id){
        ApiResponse<ClothDetailResponse> response = clothService.getClothDetail(id);

        if (response.statusCode.equals("200")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
