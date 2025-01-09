package uz.dev.salarybot.bot;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;


public class MyTelegramBot extends SpringWebhookBot {

    private final String botToken;
    private final String botUsername;
    private final String botPath;

    public MyTelegramBot(SetWebhook setWebhook, String botToken, String botUsername, String botPath) {
        super(setWebhook);
        this.botToken = botToken;
        this.botUsername = botUsername;
        this.botPath = botPath;


    }


    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            String receivedText = update.getMessage().getText();
            String replyText = "Siz yuborgan xabar: " + receivedText;
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText(replyText);
            return sendMessage;
        }
        return null;
    }

    @Override
    public String getBotPath() {
        return this.botPath;
    }

    @Override
    public String getBotUsername() {
        return this.botUsername;
    }
    @Override
    public String getBotToken() {
        return this.botToken;
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }
//    private String botUsername;
//    private String botToken;
//    private String webhookPath;
//
//
//    @Override
//    public String getBotUsername() {
//        return botUsername;
//    }
//
//    public void setBotUsername(String botUsername) {
//        this.botUsername = botUsername;
//    }
//
//    @Override
//    public String getBotToken() {
//        return botToken;
//    }
//
//    public void setBotToken(String botToken) {
//        this.botToken = botToken;
//    }
//
//    @Override
//    public String getBotPath() {
//        return webhookPath;
//    }
//
//    public void setWebhookPath(String webhookPath) {
//        this.webhookPath = webhookPath;
//    }
//
//    @Override
//    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
//        if (update.hasMessage()) {
//            Message message = update.getMessage();
//            if (message.hasText()) {
//                String responseText = "You said: " + message.getText();
//                SendMessage sendMessage = new SendMessage();
//                sendMessage.setChatId(message.getChatId().toString());
//                sendMessage.setText(responseText);
//                return sendMessage;
//            }
//        }
//        return null;
//    }
}
