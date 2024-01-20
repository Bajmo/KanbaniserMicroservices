package me.bajmo.kanbaniser.services.auth.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignInRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

}