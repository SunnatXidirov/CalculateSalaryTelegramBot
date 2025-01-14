package uz.dev.salarybot.service;


import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.dev.salarybot.dto.RequestUserDto;
import uz.dev.salarybot.entity.Database;
import uz.dev.salarybot.entity.Page;
import uz.dev.salarybot.entity.Roles;
import uz.dev.salarybot.entity.User;
import uz.dev.salarybot.entity.recordClasses.CostCategory;
import uz.dev.salarybot.repsitory.RoleRepository;
import uz.dev.salarybot.repsitory.UserRepository;
import uz.dev.salarybot.utils.RefreshTokenRequest;
import uz.dev.salarybot.utils.ResponseToken;
import uz.dev.salarybot.utils.UUIDGenerator;
import uz.dev.salarybot.utils.jwt.TokenService;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class AuthService implements UserDetailsService {

    private final String databaseId = "17a91cb8af44806c9efee87ed7f3105d";
    private final String roleDatabaseId = "17a91cb8af4480a4b157ca44c1670be2";
    private final TokenService accessTokenService;
    private final TokenService refreshTokenService;
    private final DatabaseService databaseService;

    public AuthService(@Qualifier("accessTokenService") TokenService accessTokenService,
                       @Qualifier("refreshTokenService") TokenService refreshTokenService,
                       UserRepository userRepository,
                       RoleRepository roleRepository, DatabaseService databaseService) {
        this.accessTokenService = accessTokenService;
        this.refreshTokenService = refreshTokenService;
        this.databaseService = databaseService;
    }


    public ResponseToken refreshToken(RefreshTokenRequest tokenRequest) {
        String refreshToken = tokenRequest.token();

        if (refreshToken.length() < 155)
            throw new IllegalArgumentException("Refresh token not equals");

        if (refreshTokenService.isValid(refreshToken))
            throw new IllegalArgumentException("Refresh token invalid");

        String subject = accessTokenService.getSubject(refreshToken);
        UserDetails userDetails = loadUserByUsername(subject);
        String accessToken = accessTokenService.generateToken(userDetails);
        return new ResponseToken("Bearer", accessToken, tokenRequest.token());
    }

    public List<Page> getAll() {
        Database database = databaseService.queryGetAll(databaseId);
        return database.getPages();
    }

    public User getById(String id) {
        Database database = databaseService.queryGetId(databaseId, id);
        List<Page> pages = database.getPages();
        for (Page page : pages) {
            if (page.getProperties() == null)
                throw new UsernameNotFoundException("this user not found");
        }
        User user = new User();
        String roleId = null;
        for (Page page : pages) {
            user.setUserId(UUID.fromString(page.getProperties().get("id").asText()));
            user.setName(page.getProperties().get("name").asText());
            user.setLogin(page.getProperties().get("login").asText());
            user.setLogin(page.getProperties().get("password").asText());
            user.setLogin(page.getProperties().get("chatId").asText());
            roleId = page.getProperties().get("role").asText();
        }
        Roles role = getRoleByPage(roleId);
        user.setRole(role);
        return user;
    }

    public String addUser(RequestUserDto dto) {
        User user = new User();
        Database database = databaseService.loadUserByLogin(databaseId, dto.login());
        List<Page> pages = database.getPages();
        for (Page page : pages) {
            if (page.getProperties() != null) {
                throw new UsernameNotFoundException("by this login: {}" + dto.login() + " user already exists");
            }
        }
        Roles role = getRole(dto.code());
        user.setUserId(UUID.fromString(UUIDGenerator.generateUUID()));
        user.setName(dto.name());
        user.setLogin(dto.login());
        user.setPassword(generateCode());
        user.setRole(role);
        return databaseService.addUser(databaseId, user);
    }

    public String update(RequestUserDto dto) {
        Database database = databaseService.loadUserByLogin(databaseId, dto.login());
        User user = new User();
        List<Page> pages = database.getPages();
        for (Page page : pages) {
            if (page.getProperties() == null) {
                throw new UsernameNotFoundException("user not found");
            }
        }

        return databaseService.updateUser(databaseId, dto);
    }


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Database database = databaseService.loadUserByLogin(databaseId, login);
        User user = new User();
        List<Page> pages = database.getPages();
        for (Page page : pages) {
            if (page.getProperties() == null) {
                throw new UsernameNotFoundException("by this login: {}" + login + " user not found");
            }
        }
        String roleId = null;
        for (Page page : pages) {
            user.setUserId(UUID.fromString(page.getProperties().get("id").asText()));
            user.setName(page.getProperties().get("name").asText());
            user.setLogin(page.getProperties().get("login").asText());
            user.setLogin(page.getProperties().get("password").asText());
            user.setLogin(page.getProperties().get("chatId").asText());
            roleId = page.getProperties().get("role").asText();
        }
        Roles roleByPage = getRoleByPage(roleId);
        user.setRole(roleByPage);
        return new uz.dev.salarybot.entity.UserDetails(user);
    }


    private String generateCode() {
        return String.format("%04d", new Random().nextInt(999999));
    }

    private Roles getRole(String code) {
        Database database = databaseService.findRoles(databaseId, code);
        List<Page> pages = database.getPages();
        Roles roles = new Roles();
        for (Page page : pages) {
            roles.setId(UUID.fromString(page.getProperties().get("id").asText()));
            roles.setName(page.getProperties().get("name").asText());
            roles.setName(page.getProperties().get("code").asText());
        }
        return roles;
    }

    private Roles getRoleByPage(String roleId) {
        Database database = databaseService.queryGetId(roleDatabaseId, roleId);
        List<Page> pages = database.getPages();
        Roles roles = new Roles();
        for (Page page : pages) {
            roles.setId(UUID.fromString(page.getProperties().get("id").asText()));
            roles.setName(page.getProperties().get("name").asText());
            roles.setName(page.getProperties().get("code").asText());
        }
        return roles;
    }
}



