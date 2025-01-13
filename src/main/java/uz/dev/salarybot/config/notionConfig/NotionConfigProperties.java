package uz.dev.salarybot.config.notionConfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "notion.database")
public record NotionConfigProperties(
        String baseUrl,
        String version,
        String token,
        String id) {

}
