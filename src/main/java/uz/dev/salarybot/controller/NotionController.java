package uz.dev.salarybot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.dev.salarybot.config.notionConfig.NotionClient;
import uz.dev.salarybot.config.notionConfig.NotionConfigProperties;
import uz.dev.salarybot.entity.Page;
import uz.dev.salarybot.entity.recordClasses.CostCategory;
import uz.dev.salarybot.service.CostCategoryService;

import java.util.List;

@RestController
@RequestMapping("/notion")
public class NotionController {


    private final NotionClient client;
    private final NotionConfigProperties notionConfigProperties;
    private final CostCategoryService costCategoryService;

    public NotionController(NotionConfigProperties notionConfigProperties, NotionClient client, CostCategoryService costCategoryService) {
        this.notionConfigProperties = notionConfigProperties;
        this.client = client;
        this.costCategoryService = costCategoryService;
    }

    @GetMapping()
    public List<CostCategory> findAll() {
        List<Page> pages = client.databases.query(notionConfigProperties.id());
        return pages.stream().map(costCategoryService::mapPageToCostCategory).toList();
    }
}
