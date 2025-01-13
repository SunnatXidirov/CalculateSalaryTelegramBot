package uz.dev.salarybot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.dev.salarybot.dto.RequestDto;
import uz.dev.salarybot.dto.TransactionTypeDto;
import uz.dev.salarybot.entity.Page;
import uz.dev.salarybot.entity.recordClasses.TransactionType;
import uz.dev.salarybot.service.TransactionTypeService;

import java.util.List;

@RestController
@RequestMapping("/TransactionType")
public class TransactionTypeController {

    private final TransactionTypeService typeService;

    public TransactionTypeController(TransactionTypeService typeService) {
        this.typeService = typeService;
    }


    @PostMapping("/add")
    public ResponseEntity<?> addTypesOfService(@RequestBody RequestDto typesDto) {
        return ResponseEntity.ok(typeService.save(typesDto));
    }

    @PutMapping("/notion/update")
    public ResponseEntity<?> updateTypesOfService(@RequestBody TransactionType typesDto) {
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
