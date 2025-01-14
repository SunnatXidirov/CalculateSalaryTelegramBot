package uz.dev.salarybot.service;


import org.springframework.http.*;
import org.springframework.stereotype.Service;


import org.springframework.web.client.RestTemplate;
import uz.dev.salarybot.config.notionConfig.NotionConfigProperties;
import uz.dev.salarybot.dto.RequestDto;
import uz.dev.salarybot.dto.RequestUserDto;
import uz.dev.salarybot.dto.TransactionDto;
import uz.dev.salarybot.entity.Database;
import uz.dev.salarybot.entity.User;
import uz.dev.salarybot.utils.UUIDGenerator;

import java.time.LocalDateTime;


@Service
public class DatabaseService {

    private final RestTemplate restTemplate;
    private final NotionConfigProperties notionConfigProps;

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

    public Database queryGetId(String databaseId, String id) {
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
                """, databaseId, UUIDGenerator.generateUUID(), requestDto.name(), LocalDateTime.now().toString(), LocalDateTime.now().toString());
        HttpEntity<String> entity = new HttpEntity<>(body, getDefaultHeaders());
        ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.POST, entity, String.class
        );
        return response.getBody();
    }

    public String update(String databaseId,
                         String id,
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


    public Database queryGetChatId(String databaseId, Long chatId) {
        String filterBody = String.format("""
                {
                    "filter": {
                        "property": "chatId",
                        "rich_text": {
                            "equals": "%s"
                        }
                    }
                }
                """, chatId);

        String url = notionConfigProps.baseUrl() + "/databases/" + databaseId + "/query";
        ResponseEntity<Database> db = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(filterBody, getDefaultHeaders()),
                Database.class);
        return db.getBody();
    }

    public Database loadUserByLogin(String databaseId, String login) {
        String filterBody = String.format("""
                {
                    "filter": {
                        "property": "login",
                        "rich_text": {
                            "equals": "%s"
                        }
                    }
                }
                """, login);

        String url = notionConfigProps.baseUrl() + "/databases/" + databaseId + "/query";
        ResponseEntity<Database> db = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(filterBody, getDefaultHeaders()),
                Database.class);
        return db.getBody();
    }

    public Database findRoles(String databaseId, String code) {
        String filterBody = String.format("""
                {
                    "filter": {
                        "property": "code",
                        "rich_text": {
                            "equals": "%s"
                        }
                    }
                }
                """, code);

        String url = notionConfigProps.baseUrl() + "/databases/" + databaseId + "/query";
        ResponseEntity<Database> db = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(filterBody, getDefaultHeaders()),
                Database.class);
        return db.getBody();
    }

    private HttpHeaders getDefaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + notionConfigProps.token());
        headers.set("Notion-Version", notionConfigProps.version());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public Database queryGetByPassword(String databaseId, String password) {
        String filterBody = String.format("""
                {
                    "filter": {
                        "property": "password",
                        "rich_text": {
                            "equals": "%s"
                        }
                    }
                }
                """, password);

        String url = notionConfigProps.baseUrl() + "/databases/" + databaseId + "/query";
        ResponseEntity<Database> db = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(filterBody, getDefaultHeaders()),
                Database.class);
        return db.getBody();
    }

    public String addUser(String databaseId, User user) {
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
                        "login": {
                            "date": {
                                "start": "%s"
                            }
                        },
                         "role": {
                            "relation": [
                                {"id": "%s"}
                            ]
                        },
                        "password": {
                            "date": {
                                "start": "%s"
                            }
                        }
                    }
                }
                """, databaseId, user.getUserId(), user.getName(), user.getLogin(), user.getRole().getId(), user.getPassword());

        HttpEntity<String> entity = new HttpEntity<>(body, getDefaultHeaders());
        ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.POST, entity, String.class
        );
        return response.getBody();
    }

    public String updateUser(String databaseId, RequestUserDto dto) {
        String url = notionConfigProps.baseUrl() + "/pages";
        String body = String.format("""
                {
                    "parent": {
                        "database_id": "%s"
                    },
                    "properties": {
                        "name": {
                            "rich_text": [
                                {
                                    "text": {
                                        "content": "%s"
                                    }
                                }
                            ]
                        },
                        "login": {
                            "date": {
                                "start": "%s"
                            }
                        },
                        "password": {
                            "date": {
                                "start": "%s"
                            }
                        }
                    }
                }
                """, databaseId, dto.name(), dto.login(), dto.password());

        HttpEntity<String> entity = new HttpEntity<>(body, getDefaultHeaders());
        ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.POST, entity, String.class
        );
        return response.getBody();
    }

    public String saveTransaction(String databaseId, TransactionDto dto) {
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
                        "totalsum": {
                            "rich_text": [
                                {
                                    "text": {
                                        "content": "%s"
                                    }
                                }
                            ]
                        },
                        "date": {
                            "date": {
                                "start": "%s"
                            }
                        },
                        "cost_category_type": {
                            "relation": [
                                {"id": "%s"}
                            ]
                        }
                    }
                }
                """, databaseId, UUIDGenerator.generateUUID(), dto.totalSum(), dto.dateTime(), dto.costCategoryType());

        HttpEntity<String> entity = new HttpEntity<>(body, getDefaultHeaders());
        ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.POST, entity, String.class
        );
        return response.getBody();
    }
}