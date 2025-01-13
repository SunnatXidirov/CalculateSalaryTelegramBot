package uz.dev.salarybot.entity;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class User {
    private Long userId;
    private String name;
    private String login;
    private String password;
    private Set<String> rolesId;
}
