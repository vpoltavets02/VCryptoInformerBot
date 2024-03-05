package v.crypto.informer.service.impl;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import v.crypto.informer.bot.VCryptoInformerBot;
import v.crypto.informer.service.SendBotMessageService;

@Log4j
@Service
public class SendBotMessageServiceImpl implements SendBotMessageService {
    private final VCryptoInformerBot bot;

    public SendBotMessageServiceImpl(VCryptoInformerBot bot) {
        this.bot = bot;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        sendMessage.enableHtml(true);
        try {
            bot.execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Something went wrong while message sending", e);
        }
    }
}