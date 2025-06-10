package com.example.ClothesSalesStore.Services;

import com.example.ClothesSalesStore.DTOModels.ApiResponse;
import com.example.ClothesSalesStore.DTOModels.Authentication.LoginRequest;
import com.example.ClothesSalesStore.DTOModels.Authentication.AuthResponse;
import com.example.ClothesSalesStore.DTOModels.Authentication.RegisterRequest;
import com.example.ClothesSalesStore.DTOModels.Clothes.ClothViewResponse;
import com.example.ClothesSalesStore.Entities.User;
import com.example.ClothesSalesStore.Repositories.UserRepository;
import com.example.ClothesSalesStore.Services.Interface.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService implements IAuthenticationService {

    private final UserRepository _userRepository;


    @Autowired
    public AuthenticationService(UserRepository _userRepository) {
        this._userRepository = _userRepository;
    }

    @Override
    public ApiResponse<AuthResponse> Login(LoginRequest request){
        try{
            User user = _userRepository.findByUsername(request.getUsername());

            if(user==null){
                return ApiResponse.<AuthResponse>builder()
                        .statusCode("404")
                        .Data(null)
                        .message("Login failed - Can't find the User with this username")
                        .build();
            }
            if(!user.getPassword().equals(request.getPassword())){
                return ApiResponse.<AuthResponse>builder()
                        .statusCode("404")
                        .Data(null)
                        .message("Login failed - Incorrect Password")
                        .build();
            }

            AuthResponse response= AuthResponse.builder()
                    .data("N/A")
                    .message("Login Successful")
                    .build();

            return ApiResponse.<AuthResponse>builder()
                    .statusCode("200")
                    .Data(response)
                    .message("Operation successful")
                    .build();

        }catch(Exception e){
            return ApiResponse.<AuthResponse>builder()
                    .statusCode("404")
                    .Data(null)
                    .message("Error execute logging in " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ApiResponse<AuthResponse> Register(RegisterRequest request) {

        try{

            if(request.getPassword().length()<8){
                return ApiResponse.<AuthResponse>builder()
                        .statusCode("200")
                        .Data(null)
                        .message("Register failed - Password is length must be equal or more than 8")
                        .build();
            }


            if(request.getPassword().equals(request.getConfirmPassword())){
                return ApiResponse.<AuthResponse>builder()
                        .statusCode("200")
                        .Data(null)
                        .message("Register failed - Confirm password isn't match with password")
                        .build();
            }


            User user = new User();
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            user.setPhoneNumber(request.getPhoneNumber());
            user.setAddress(request.getAddress());
            user.setRole("Customer");

            _userRepository.save(user);

            AuthResponse response= AuthResponse.builder()
                    .data("N/A")
                    .message("Register Successful")
                    .build();

            return ApiResponse.<AuthResponse>builder()
                    .statusCode("201")
                    .Data(response)
                    .message("Operation successful")
                    .build();

        }catch(Exception e){
            return ApiResponse.<AuthResponse>builder()
                    .statusCode("404")
                    .Data(null)
                    .message("Error execute register " + e.getMessage())
                    .build();
        }
    }
}
