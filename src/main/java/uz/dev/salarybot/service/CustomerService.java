package uz.dev.salarybot.service;

import org.springframework.stereotype.Service;
import uz.dev.salarybot.dto.RequestDto;
import uz.dev.salarybot.entity.Database;
import uz.dev.salarybot.entity.Page;
import uz.dev.salarybot.entity.recordClasses.MoneyType;

import java.util.List;

@Service
public class CustomerService {

    private final String databaseId = "17a91cb8af4480cf9f74e3da445ed58a";
    private final DatabaseService databaseService;

    public CustomerService(DatabaseService databaseService) {
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

    public String update(MoneyType moneyType) {
        return databaseService.update(databaseId, String.valueOf(moneyType.id()),
                moneyType.name(), moneyType.createdAt(), moneyType.updatedAt());
    }
}
