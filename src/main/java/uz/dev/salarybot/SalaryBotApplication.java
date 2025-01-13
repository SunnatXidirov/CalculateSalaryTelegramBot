package uz.dev.salarybot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import uz.dev.salarybot.config.notionConfig.NotionConfigProperties;

@SpringBootApplication
@EnableConfigurationProperties(NotionConfigProperties.class)
public class SalaryBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalaryBotApplication.class, args);
    }

}
