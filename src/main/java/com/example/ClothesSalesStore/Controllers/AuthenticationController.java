package com.example.ClothesSalesStore.Controllers;


import com.example.ClothesSalesStore.DTOModels.ApiResponse;
import com.example.ClothesSalesStore.DTOModels.Authentication.LoginRequest;
import com.example.ClothesSalesStore.DTOModels.Authentication.AuthResponse;
import com.example.ClothesSalesStore.DTOModels.Authentication.RegisterRequest;
import com.example.ClothesSalesStore.Services.Interface.IAuthenticationService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final IAuthenticationService _authenticationService;

    @Autowired
    public AuthenticationController(IAuthenticationService authenticationService) {
        _authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> Login(
            @RequestBody LoginRequest request
            ){
        ApiResponse<AuthResponse> response=_authenticationService.Login(request);
        if (response.statusCode.equals("200")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> Register(@RequestBody RegisterRequest request){

        if(request==null){
            System.out.println("Request is null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ApiResponse.<AuthResponse>builder()
                            .statusCode("400")
                            .Data(null)
                            .message("Request body cannot be null")
                            .build()
            );
        }



        ApiResponse<AuthResponse> response = _authenticationService.Register(request);
        if (response.statusCode.equals("201")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
