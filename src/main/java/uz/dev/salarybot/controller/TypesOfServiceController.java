package uz.dev.salarybot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.dev.salarybot.dto.TypesOfServiceDto;

@RestController
@RequestMapping("/notion/TypesOfService")
public class TypesOfServiceController {

    @PostMapping("/add")
    public ResponseEntity<?> addTypesOfService(@RequestBody TypesOfServiceDto typesDto){

        return null;
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTypesOfService(@RequestBody TypesOfServiceDto typesDto){

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
