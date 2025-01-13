package uz.dev.salarybot.utils;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequest(@NotBlank(message = "token is required") String token) {

}