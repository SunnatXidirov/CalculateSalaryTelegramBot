package uz.dev.salarybot.dto;

public record RequestUserDto(
        String name,
        String login,
        String password,
        String code
) {
}
