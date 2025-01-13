package uz.dev.salarybot.dto;

import java.time.LocalDateTime;

public record TypesOfServiceDto(String name, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
