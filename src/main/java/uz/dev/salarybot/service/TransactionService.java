package uz.dev.salarybot.service;

import org.springframework.stereotype.Service;
import uz.dev.salarybot.dto.RequestDto;
import uz.dev.salarybot.dto.TransactionDto;
import uz.dev.salarybot.entity.Database;
import uz.dev.salarybot.entity.Page;
import uz.dev.salarybot.entity.recordClasses.MoneyType;

import java.util.List;

@Service
public class TransactionService {
    private final String databaseId = "17a91cb8af448059bc64ea3a3a1f084a";
    private final DatabaseService databaseService;

    public TransactionService(DatabaseService databaseService) {
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

    public String save(TransactionDto dto) {
        return databaseService.saveTransaction(databaseId, dto);
    }


}
