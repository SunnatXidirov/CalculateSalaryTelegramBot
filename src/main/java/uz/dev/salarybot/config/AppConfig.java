package uz.dev.salarybot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import uz.dev.salarybot.config.notionConfig.NotionConfigProperties;

@Configuration
public class AppConfig {

    private final NotionConfigProperties notionConfigProps;

    public AppConfig(NotionConfigProperties notionConfigProperties) {
        this.notionConfigProps = notionConfigProperties;
    }


    @Bean
    public RestTemplate restTemplate() {


        return new RestTemplate();
    }

    private HttpHeaders getDefaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + notionConfigProps.token());
        headers.set("Notion-Version", notionConfigProps.version());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }



    @Bean
    public SendMessage sendMessage() {
        return new SendMessage();
    }
}