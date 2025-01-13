package uz.dev.salarybot.utils;

import lombok.Builder;

@Builder
public record ResponseToken(String tokenType, String token, String refreshToken) {
}
