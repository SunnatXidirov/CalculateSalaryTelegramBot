package uz.dev.salarybot.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

public class Roles implements GrantedAuthority {
    private UUID id;
    private String name;
    private String code;


    @Override
    public String getAuthority() {
        return "ROLE_" + this.code;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
