package uz.dev.salarybot.service;

import org.springframework.stereotype.Service;
import uz.dev.salarybot.dto.RequestDto;
import uz.dev.salarybot.entity.Database;
import uz.dev.salarybot.entity.Page;
import uz.dev.salarybot.entity.recordClasses.MoneyType;
import uz.dev.salarybot.entity.recordClasses.TransactionType;

import java.util.List;

@Service
public class TransactionTypeService {

    private final String databaseId="17a91cb8af4480b09263ceedfd43694b";
    private final DatabaseService databaseService;


    public TransactionTypeService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public List<Page> getAll() {
        Database database = databaseService.queryGetAll(databaseId);
        return database.getPages();
    }

    public List<Page> getById(Long id) {
        Database database = databaseService.queryGetId(databaseId, id);
        return database.getPages();
    }

    public String save(RequestDto dto) {
        return databaseService.saveByNameAndId(databaseId, dto);
    }

    public String update(TransactionType TransactionType) {
        return databaseService.update(databaseId,TransactionType.id(),
                TransactionType.name(), TransactionType.createdAt(),TransactionType.updatedAt());
    }

}
