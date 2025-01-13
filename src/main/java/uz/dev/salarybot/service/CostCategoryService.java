package uz.dev.salarybot.service;

import org.springframework.stereotype.Service;
import uz.dev.salarybot.entity.Page;
import uz.dev.salarybot.entity.recordClasses.CostCategory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CostCategoryService {
    public CostCategory mapPageToCostCategory(Page page) {
        return new CostCategory(
                Long.parseLong(page.getId()),
                page.getProperties().get("Name").get("name").get(0).get("content").asText(),
                LocalDateTime.parse(page.getProperties().get("createdAt")
                        .get("date").get("start").asText(), DateTimeFormatter.ISO_OFFSET_DATE_TIME),
                LocalDateTime.parse(page.getProperties().get("updatedAt")
                        .get("date").get("start").asText(), DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        );
    }
}
