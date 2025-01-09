package uz.dev.salarybot.service;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Service
public class BotService {


    public Boolean checkUser(Long chatId) {
        // userni databasedan tekshirish kerak

        return false;
    }

    public BotApiMethod<?> enterMenu(Update update) {

        Long chatId = getChatId(update);
        String messageText = getMessageText(update);

        if (messageText.equalsIgnoreCase("/start")) {
            return sendMessage(chatId, "Xush kelibsiz! Parolingizni kiriting:");
        } else if (isValidPassword(messageText)) {
            //do user save
            return sendMainMenu(chatId);
        } else {
            return sendMessage(chatId, "Noto'g'ri parol! Iltimos, qaytadan kiriting.");
        }


    }

    private BotApiMethod<?> sendMainMenu(Long chatId) {
        InlineKeyboardMarkup markup = InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(
                        InlineKeyboardButton.builder().text("Hisobotlar").callbackData("reports").build(),
                        InlineKeyboardButton.builder().text("Sozlamalar").callbackData("settings").build()
                ))
                .build();

        return SendMessage.builder()
                .chatId(chatId.toString())
                .text("Xush kelibsiz! Asosiy menyudan tanlang:")
                .replyMarkup(markup)
                .build();
    }


    private BotApiMethod<?> menu(Long chatId) {

        InlineKeyboardButton testButton = InlineKeyboardButton.builder()
                .text("Test")
                .callbackData("test_button")
                .build();

        InlineKeyboardButton checkButton = InlineKeyboardButton.builder()
                .text("Check")
                .callbackData("check_button")
                .build();

        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(testButton);
        row.add(checkButton);

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(row);

        InlineKeyboardMarkup markup = InlineKeyboardMarkup.builder()
                .keyboard(keyboard)
                .build();

        return SendMessage.builder()
                .chatId(chatId.toString())
                .text("Inline tugmalarni tanlang:")
                .replyMarkup(markup)
                .build();
    }

    private boolean isValidPassword(String password) {
        return password.equals("1234");
    }


    private BotApiMethod<?> handleAuthenticatedUser(Long chatId, String messageText) {
        if (messageText.equals("/menu")) {
            return sendMainMenu(chatId);
        } else {
            return sendMessage(chatId, "Mavjud bo'lmagan komanda. Asosiy menyuga o'tish uchun /menu yozing.");
        }
    }


    private Long getChatId(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getChatId();
        } else if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getMessage().getChatId();
        }
        return null;
    }

    private String getMessageText(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getText();
        } else if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getData();
        }
        return "";
    }

    private BotApiMethod<?> sendMessage(Long chatId, String text) {
        return SendMessage.builder()
                .chatId(chatId.toString())
                .text(text)
                .build();
    }

}
