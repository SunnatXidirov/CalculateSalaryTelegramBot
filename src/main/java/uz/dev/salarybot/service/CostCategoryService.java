package uz.dev.salarybot.service;


import org.springframework.stereotype.Service;
import uz.dev.salarybot.dto.RequestDto;
import uz.dev.salarybot.entity.Database;
import uz.dev.salarybot.entity.Page;
import uz.dev.salarybot.entity.recordClasses.CostCategory;



import java.util.List;


@Service
public class CostCategoryService {

    private final String databaseId = "17a91cb8af4480218c5bea6edbd2359b";
    private final DatabaseService databaseService;

    public CostCategoryService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }


    public List<Page> getAll() {
        Database database = databaseService.queryGetAll(databaseId);
        return database.getPages();
    }

    public List<Page> getById(String id) {
        Database database = databaseService.queryGetId(databaseId, id);
        return database.getPages();
    }

    public String save(RequestDto dto) {
        return databaseService.saveByNameAndId(databaseId, dto);
    }

    public String update(CostCategory costCategory) {
       return databaseService.update(databaseId,String.valueOf(costCategory.getId()),
               costCategory.getName(),costCategory.getCreatedAt(),costCategory.getUpdatedAt());
    }
}
