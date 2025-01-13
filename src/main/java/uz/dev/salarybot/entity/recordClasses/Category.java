package uz.dev.salarybot.entity.recordClasses;

import java.time.LocalDateTime;

public record Category(Long id, String name, LocalDateTime createdAt,LocalDateTime updatedAt) {

}
