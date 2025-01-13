package uz.dev.salarybot.entity;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class Transaction {

    private Long id;
    private BigDecimal totalSum;
    private LocalDateTime date;
    private String costCategoryType;

}
