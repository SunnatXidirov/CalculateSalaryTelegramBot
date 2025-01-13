package uz.dev.salarybot.entity.recordClasses;

import java.time.LocalDateTime;

public record CostCategory(Long id, String name, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
