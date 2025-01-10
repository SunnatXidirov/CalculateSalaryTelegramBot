package uz.dev.salarybot.service;


import lombok.extern.slf4j.Slf4j;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;


import java.util.List;

@Service
@Slf4j
public class BotService {
    private final Logger logger = LoggerFactory.getLogger(BotService.class);

    public BotApiMethod<?> checkedUser(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            Long chatId = update.getMessage().getChatId();
            String messageText = update.getMessage().getText();

            if (checkUser(chatId)) {
                if (messageText.equalsIgnoreCase("/start")) {
                    return sendMessage(chatId, "Xush kelibsiz! \n Parolingizni kiriting:");
                } else if (isValidPassword(messageText)) {
                    //do user save
                    return sendMainMenu(chatId);
                } else {
                    return sendMessage(chatId, "Noto'g'ri parol! Iltimos, qaytadan kiriting.");
                }
            }

        } else if (update.hasCallbackQuery()) {
            Long chatId = update.getCallbackQuery().getMessage().getChatId();
            String data = update.getCallbackQuery().getData();
            return handleAuthenticatedUser(chatId, data);
        }
        return sendMainMenu(update.getMessage().getChatId());
    }

    private boolean checkUser(Long ChatId) {
        ///  userni databasedan tekshirish kerak authentication
        return false;
    }


    private BotApiMethod<?> sendMainMenu(Long chatId) {

        InlineKeyboardMarkup markup = InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(
                        InlineKeyboardButton.builder().text("\uD83D\uDDC2Hisobotlar").callbackData("hisobotlar").build(),
                        InlineKeyboardButton.builder().text("⚙\uFE0FSozlamalar").callbackData("sozlamalar").build()
                ))
                .build();

        return SendMessage.builder()
                .chatId(chatId.toString())
                .text("Xush kelibsiz! Asosiy menyudan tanlang:")
                .replyMarkup(markup)
                .build();
    }

    public boolean isValidPassword(String password) {
        /// passwordni databasedan tekshirish kerak
        return password.equals("1234");
    }

    public BotApiMethod<?> handleAuthenticatedUser(Long chatId, String messageText) {

        switch (messageText) {
            case "hisobotlar" -> {
                return sendHisobotlarMenu(chatId);
            }
            case "sozlamalar" -> {
                return sozlamalarMenu(chatId);
            }
            case "oylik_harajatlar" -> {
                return oylik_harajatlar(chatId);
            }
            case "oylik_daromadlar" -> {
                return oylik_daromadlar(chatId);
            }
            case "Qhisobotlar" -> {
                return qoshimcha_hisobotlar(chatId);
            }
            case "profile" -> {
                return profile(chatId);
            }
            case "huquqlar" -> {
                return kirish_huquqlar(chatId);
            }
            case "parol" -> {
                return change_password(chatId);
            }
        }

        return sendMainMenu(chatId);
    }


    private BotApiMethod<?> sendHisobotlarMenu(Long chatId) {
        InlineKeyboardMarkup markup = InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(
                        InlineKeyboardButton.builder().text("\uD83D\uDCB0Oylik Daromadlar").callbackData("oylik_daromadlar").build(),
                        InlineKeyboardButton.builder().text("\uD83D\uDCB8Oylik Harajatlar").callbackData("oylik_harajatlar").build()
                ))
                .keyboardRow(List.of(
                        InlineKeyboardButton.builder().text("\uD83D\uDDD2Qo'shimcha Hisobotlar").callbackData("Qhisobotlar").build()
                ))
                .build();

        return SendMessage.builder()
                .chatId(chatId.toString())
                .text("Xush kelibsiz! Hisobotni tanlang")
                .replyMarkup(markup)
                .build();

    }

    private BotApiMethod<?> oylik_daromadlar(Long chatId) {

        return sendMessage(chatId, "oylik daromadlar menusi");
    }

    private BotApiMethod<?> oylik_harajatlar(Long chatId) {
        return sendMessage(chatId, "oylik harajatlar menusi");
    }

    private BotApiMethod<?> qoshimcha_hisobotlar(Long chatId) {
        return sendMessage(chatId, "qo'shimcha hisobotlar menusi");
    }


    private BotApiMethod<?> sozlamalarMenu(Long chatId) {
        InlineKeyboardMarkup markup = InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(
                        InlineKeyboardButton.builder().text("\uD83D\uDC64Proflie ma'lumotlari").callbackData("profile").build(),
                        InlineKeyboardButton.builder().text("✅Kirish huquqlar").callbackData("huquqlar").build()
                        ))
                .keyboardRow(List.of(
                        InlineKeyboardButton.builder().text("\uD83D\uDD04Parolni o'zgartirish").callbackData("parol").build()
                ))
                .build();

        return SendMessage.builder()
                .chatId(chatId.toString())
                .text("Xush kelibsiz! Menu tanlang")
                .replyMarkup(markup)
                .build();
    }

    private BotApiMethod<?> change_password(Long chatId) {

        return sendMessage(chatId, "change password menusi");
    }

    private BotApiMethod<?> kirish_huquqlar(Long chatId) {
        return sendMessage(chatId, "kirish huquqlari menusi");
    }

    private BotApiMethod<?> profile(Long chatId) {
        return sendMessage(chatId, "prifile menusi");
    }


    public Long getChatId(Update update) {
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
