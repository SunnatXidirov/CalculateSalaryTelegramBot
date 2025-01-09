package uz.dev.salarybot.controller.TelegramBot;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import uz.dev.salarybot.service.BotService;


import java.util.ArrayList;
import java.util.List;


@RestController
@EnableAsync
@RequiredArgsConstructor
@RequestMapping("/webhook")
public class BotController {

    private final BotService botService;

    @PostMapping
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {

        if (botService.checkUser(update.getMessage().getChatId())) {
            botService.enterMenu(update);
        } else {
            sendMessage(update.getMessage().getChatId(), "Enter Password:");
        }
        return sendMessage(update.getMessage().getChatId(), "http stats code :500");
    }

    private BotApiMethod<?> sendMessage(Long chatId, String text) {
        return SendMessage.builder()
                .chatId(chatId.toString())
                .text(text)
                .build();
    }
}


