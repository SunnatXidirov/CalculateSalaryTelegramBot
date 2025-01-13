package uz.dev.salarybot.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDto(BigDecimal totalSum, String costCategoryType, LocalDateTime dateTime) {
}
