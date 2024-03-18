package v.crypto.informer.service.impl;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import v.crypto.informer.bot.VCryptoInformerBot;
import v.crypto.informer.keyboards.KeyboardMarkups;
import v.crypto.informer.service.SendBotMessageService;

@Log4j
@Service
public class SendBotMessageServiceImpl implements SendBotMessageService {
    private final KeyboardMarkups keyboardMarkups;
    private final VCryptoInformerBot bot;

    public SendBotMessageServiceImpl(KeyboardMarkups keyboardMarkups, VCryptoInformerBot bot) {
        this.keyboardMarkups = keyboardMarkups;
        this.bot = bot;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        sendMessage.setReplyMarkup(keyboardMarkups.getReplyKeyboard());
        sendMessage.enableHtml(true);
        try {
            bot.execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Something went wrong while message sending", e);
        }
    }

    @Override
    public void sendMessageWithKeyboard(String chatId, InlineKeyboardMarkup markup, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setReplyMarkup(markup);
        sendMessage.setText(message);
        sendMessage.enableHtml(true);
        try {
            bot.execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Something went wrong while message with markup sending", e);
        }
    }

    @Override
    public void editInlineMarkup(String chatId, Integer messageId, String inlineMessageId, InlineKeyboardMarkup markup) {
        EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup(chatId, messageId, inlineMessageId,
                markup);
        try {
            bot.execute(editMessageReplyMarkup);
        } catch (TelegramApiException e) {
            log.error("Something went wrong while InlineMarkup editing", e);
        }
    }
}