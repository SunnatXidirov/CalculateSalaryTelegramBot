package uz.dev.salarybot.entity;

import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class Customer {
    private UUID id;
    private String fullName;
    private String phone;
    private List<String> serviceTypesId;
}
