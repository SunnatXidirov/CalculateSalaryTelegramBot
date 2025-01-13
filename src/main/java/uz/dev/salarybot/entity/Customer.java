package uz.dev.salarybot.entity;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class Customer {
    private Long userId;
    private String fullName;
    private String phone;
    private Set<String> serviceTypesId;
}
