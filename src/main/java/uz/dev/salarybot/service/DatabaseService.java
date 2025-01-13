package uz.dev.salarybot.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import uz.dev.salarybot.config.notionConfig.NotionConfigProperties;
import uz.dev.salarybot.dto.RequestDto;
import uz.dev.salarybot.entity.Database;
import uz.dev.salarybot.entity.Page;
import uz.dev.salarybot.entity.recordClasses.CostCategory;

import java.time.LocalDateTime;


@Service
public class DatabaseService {

    private final RestTemplate restTemplate;
    private final NotionConfigProperties notionConfigProps;
    private final Logger log = LoggerFactory.getLogger(DatabaseService.class);

    public DatabaseService(RestTemplate restTemplate, NotionConfigProperties notionConfigProps) {
        this.restTemplate = restTemplate;
        this.notionConfigProps = notionConfigProps;
    }

    public Database queryGetAll(String databaseId) {
        String url = notionConfigProps.baseUrl() + "/databases/" + databaseId + "/query";
        ResponseEntity<Database> db = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(getDefaultHeaders()),
                Database.class);
        return db.getBody();
    }

    public Database queryGetId(String databaseId, Long id) {
        String filterBody = String.format("""
                {
                    "filter": {
                        "property": "id",
                        "rich_text": {
                            "equals": "%s"
                        }
                    }
                }
                """, id);

        String url = notionConfigProps.baseUrl() + "/databases/" + databaseId + "/query";
        ResponseEntity<Database> db = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(filterBody, getDefaultHeaders()),
                Database.class);
        return db.getBody();
    }

    public String saveByNameAndId(String databaseId, RequestDto requestDto) {

        String url = notionConfigProps.baseUrl() + "/pages";
        String body = String.format("""
                {
                    "parent": {
                        "database_id": "%s"
                    },
                    "properties": {
                        "id": {
                            "title": [
                                {
                                    "text": {
                                        "content": "%s"
                                    }
                                }
                            ]
                        },
                        "name": {
                            "rich_text": [
                                {
                                    "text": {
                                        "content": "%s"
                                    }
                                }
                            ]
                        },
                        "createdAt": {
                            "date": {
                                "start": "%s"
                            }
                        },
                        "updatedAt": {
                            "date": {
                                "start": "%s"
                            }
                        }
                    }
                }
                """, databaseId, requestDto.id(), requestDto.name(), LocalDateTime.now().toString(), LocalDateTime.now().toString());
        HttpEntity<String> entity = new HttpEntity<>(body, getDefaultHeaders());
        ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.POST, entity, String.class
        );
        return response.getBody();
    }

    public String update(String databaseId,
                         Long id,
                         String name,
                         LocalDateTime createdAt,
                         LocalDateTime updatedAt) {
        String url = notionConfigProps.baseUrl() + "/pages";

        String body = String.format("""
                {
                    "parent": {
                        "database_id": "%s"
                    },
                    "properties": {
                        "id": {
                            "title": [
                                {
                                    "text": {
                                        "content": "%s"
                                    }
                                }
                            ]
                        },
                        "name": {
                            "rich_text": [
                                {
                                    "text": {
                                        "content": "%s"
                                    }
                                }
                            ]
                        },
                        "createdAt": {
                            "date": {
                                "start": "%s"
                            }
                        },
                        "updatedAt": {
                            "date": {
                                "start": "%s"
                            }
                        }
                    }
                }
                """, databaseId, id, name, createdAt, updatedAt);

        HttpEntity<String> entity = new HttpEntity<>(body, getDefaultHeaders());
        ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.POST, entity, String.class
        );
        return response.getBody();
    }


    private HttpHeaders getDefaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + notionConfigProps.token());
        headers.set("Notion-Version", notionConfigProps.version());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

}