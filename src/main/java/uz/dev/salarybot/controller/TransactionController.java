package uz.dev.salarybot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.dev.salarybot.dto.TransactionDto;

@RestController
@RequestMapping("/notion/Transaction")
public class TransactionController {

    @PostMapping("/add")
    public ResponseEntity<?> addTypesOfService(@RequestBody TransactionDto typesDto){

        return null;
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTypesOfService(@RequestBody TransactionDto typesDto){

        return null;
    }


    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){

        return null;
    }
    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam Long id){

        return null;
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Long id){

        return null;
    }

}
