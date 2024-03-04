package v.crypto.informer.bot;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import v.crypto.informer.data.TokenData;

@Log4j
@Component
public class VCryptoInformerBot extends TelegramLongPollingBot {
    @Value("${bot.username}")
    private String botUsername;

    public VCryptoInformerBot(@Value("${bot.token}") String botToken) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText())
            return;
        long chatId = update.getMessage().getChatId();
        sendMessage(chatId, TokenData.getListInfo());
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    private void sendMessage(long chatId, String message) {
        try {
            execute(new SendMessage(String.valueOf(chatId), message));
        } catch (TelegramApiException e) {
            log.error("Something went wrong while message sending", e);
        }
    }
}
