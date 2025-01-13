package uz.dev.salarybot.dto;

import java.time.LocalDateTime;

public record MoneyTypeDto(String name, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
