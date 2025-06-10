package com.example.ClothesSalesStore.Services.Interface;

import com.example.ClothesSalesStore.DTOModels.ApiResponse;
import com.example.ClothesSalesStore.DTOModels.Authentication.LoginRequest;
import com.example.ClothesSalesStore.DTOModels.Authentication.AuthResponse;
import com.example.ClothesSalesStore.DTOModels.Authentication.RegisterRequest;

public interface IAuthenticationService {
    ApiResponse<AuthResponse> Login(LoginRequest request);
    ApiResponse<AuthResponse> Register(RegisterRequest request);
}
