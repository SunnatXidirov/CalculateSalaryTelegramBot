package uz.dev.salarybot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.dev.salarybot.config.notionConfig.NotionClient;
import uz.dev.salarybot.config.notionConfig.NotionConfigProperties;
import uz.dev.salarybot.dto.RequestDto;
import uz.dev.salarybot.entity.Page;
import uz.dev.salarybot.entity.recordClasses.CostCategory;
import uz.dev.salarybot.service.CostCategoryService;

import java.util.List;

@RestController
@RequestMapping("/notion/CostCategory")
public class CostCategoryController {


    private final CostCategoryService costCategoryService;

    public CostCategoryController(CostCategoryService costCategoryService) {
        this.costCategoryService = costCategoryService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> findAll() {
        List<Page> pages = costCategoryService.getAll();
        return ResponseEntity.ok(pages);
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam String id) {
        List<Page> page = costCategoryService.getById(id);
        return ResponseEntity.ok(page);
    }


    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestParam RequestDto dto) {
        return ResponseEntity.ok(costCategoryService.save(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTypesOfService(@RequestBody CostCategory costCategory) {
        return ResponseEntity.ok(costCategoryService.update(costCategory));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Long id) {

        return null;
    }


}
