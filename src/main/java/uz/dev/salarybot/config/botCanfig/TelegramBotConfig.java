package uz.dev.salarybot.config.botCanfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import uz.dev.salarybot.bot.MyTelegramBot;


@Configuration
public class TelegramBotConfig {


    @Value("${telegram.bot.username}")
    private String botUsername;

    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.bot.path}")
    private String botPath;

    @Value("${telegram.bot.webhook-url}")
    private String webhookPath;

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder().url(webhookPath).build();
    }

    @Bean
    public MyTelegramBot myTelegramBot(SetWebhook setWebhook) {
        return new MyTelegramBot(setWebhook, botToken, botUsername, botPath);
    }


}
