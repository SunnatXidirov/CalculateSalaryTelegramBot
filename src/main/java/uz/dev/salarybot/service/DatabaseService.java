package uz.dev.salarybot.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import uz.dev.salarybot.config.notionConfig.NotionConfigProperties;
import uz.dev.salarybot.entity.Database;
import uz.dev.salarybot.entity.Page;

import java.net.UnknownServiceException;
import java.util.List;
import java.util.Objects;

@Service
public class DatabaseService {

    private final WebClient webClient;
    private final RestTemplate restTemplate;
    private final NotionConfigProperties notionConfigProps;
    private final Logger log = LoggerFactory.getLogger(DatabaseService.class);

    public DatabaseService(WebClient webClient, RestTemplate restTemplate, NotionConfigProperties notionConfigProps) {
        this.webClient = webClient;
        this.restTemplate = restTemplate;
        this.notionConfigProps = notionConfigProps;
    }

    public List<Page> query(String databaseId) {
        String url = notionConfigProps.baseUrl() + "/databases/" + databaseId + "/query";
        log.info("Querying Notion database: {}", url);
        Database database = new Database();
        Page page = new Page();
//        List<Page> pages = database.getPages();
        ResponseEntity<Database> db = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(getDefaultHeaders()),
                Database.class);

        return null;
    }

    private HttpHeaders getDefaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + notionConfigProps.token());
        headers.set("Notion-Version", notionConfigProps.version());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}