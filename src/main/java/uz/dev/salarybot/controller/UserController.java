package uz.dev.salarybot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.dev.salarybot.dto.RequestUserDto;
import uz.dev.salarybot.entity.Page;
import uz.dev.salarybot.entity.User;
import uz.dev.salarybot.service.AuthService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notion/users")
public class UserController {
    private final AuthService userService;


    public UserController(AuthService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addTypesOfService(@RequestBody RequestUserDto userDto) {
        return ResponseEntity.ok(userService.addUser(userDto));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTypesOfService(@RequestBody RequestUserDto userDto) {
        return ResponseEntity.ok(userService.update(userDto));
    }


    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        List<Page> pages = userService.getAll();
        return ResponseEntity.ok(pages);
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam UUID id) {
        User user = userService.getById(String.valueOf(id));
        return ResponseEntity.ok(user);
    }

    @GetMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Long id) {
        return null;
    }


}
