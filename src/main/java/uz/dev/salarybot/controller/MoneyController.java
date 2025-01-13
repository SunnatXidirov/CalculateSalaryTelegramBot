package uz.dev.salarybot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.dev.salarybot.dto.MoneyTypeDto;
import uz.dev.salarybot.dto.RequestDto;
import uz.dev.salarybot.entity.Page;
import uz.dev.salarybot.entity.recordClasses.MoneyType;
import uz.dev.salarybot.service.MoneyTypeService;

import java.util.List;

@RestController
@RequestMapping("/notion/Money")
public class MoneyController {

    private final MoneyTypeService typeService;

    public MoneyController(MoneyTypeService typeService) {
        this.typeService = typeService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTypesOfService(@RequestBody RequestDto typesDto) {
        return ResponseEntity.ok(typeService.save(typesDto));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTypesOfService(@RequestBody MoneyType typesDto) {
        return ResponseEntity.ok(typeService.update(typesDto));
    }


    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        List<Page> pages = typeService.getAll();
        return ResponseEntity.ok(pages);
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam Long id) {
        List<Page> pages = typeService.getById(id);
        return ResponseEntity.ok(pages);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Long id) {

        return null;
    }


}



