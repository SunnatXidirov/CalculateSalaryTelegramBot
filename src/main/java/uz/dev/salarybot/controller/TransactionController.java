package uz.dev.salarybot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.dev.salarybot.dto.TransactionDto;
import uz.dev.salarybot.entity.Page;
import uz.dev.salarybot.service.TransactionService;

import java.util.List;


@RestController
@RequestMapping("/notion/Transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTypesOfService(@RequestBody TransactionDto typesDto) {
        String save = transactionService.save(typesDto);
        return ResponseEntity.ok(save);
    }


    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        List<Page> pages = transactionService.getAll();
        return ResponseEntity.ok(pages);
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam String id) {
        List<Page> pages = transactionService.getById(id);
        return ResponseEntity.ok(pages);
    }

}
