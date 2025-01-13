package uz.dev.salarybot.dto;

import java.time.LocalDateTime;

public record TransactionTypeDto(String name, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
