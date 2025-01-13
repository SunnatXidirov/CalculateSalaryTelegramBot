package uz.dev.salarybot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.dev.salarybot.dto.CustomerDto;

@RestController
@RequestMapping("/notion/costumer")
public class CustomerController {

    @PostMapping("/add")
    public ResponseEntity<?> addTypesOfService(@RequestBody CustomerDto typesDto){

        return null;
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTypesOfService(@RequestBody CustomerDto typesDto){

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
    @GetMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Long id){

        return null;
    }



}
