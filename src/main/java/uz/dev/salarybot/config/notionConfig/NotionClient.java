package uz.dev.salarybot.config.notionConfig;
import org.springframework.stereotype.Component;
import uz.dev.salarybot.service.DatabaseService;

@Component
public class NotionClient {

    public final DatabaseService databases;

    public NotionClient(DatabaseService databases) {
        this.databases = databases;
    }

}