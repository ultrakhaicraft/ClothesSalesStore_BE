package com.example.ClothesSalesStore.DTOModels.Authentication;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
///Use for both Login and Request
public class AuthResponse {
    public String data;
    public String message;
}
