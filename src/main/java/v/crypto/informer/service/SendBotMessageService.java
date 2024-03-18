package v.crypto.informer.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface SendBotMessageService {
    void sendMessage(String chatId, String message);
    void sendMessageWithKeyboard(String chatId, InlineKeyboardMarkup markup, String message);
    void editInlineMarkup(String chatId, Integer messageId, String inlineMessageId, InlineKeyboardMarkup markup);
}