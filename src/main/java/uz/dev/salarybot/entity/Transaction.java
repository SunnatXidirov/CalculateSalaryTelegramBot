package uz.dev.salarybot.entity;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class Transaction {

    private UUID id;
    private BigDecimal totalSum;
    private LocalDateTime date;
    private String costCategoryTypeId;

}
