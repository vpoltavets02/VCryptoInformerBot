package v.crypto.informer.callback;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import v.crypto.informer.keyboards.KeyboardMarkups;
import v.crypto.informer.service.SendBotMessageService;
import v.crypto.informer.service.impl.UserService;
import v.crypto.informer.util.TextConstants;

public class AddCallback implements Callback {
    private final KeyboardMarkups keyboardMarkups;
    private final UserService userService;
    private final SendBotMessageService sendBotMessageService;

    public AddCallback(KeyboardMarkups keyboardMarkups, UserService userService, SendBotMessageService sendBotMessageService) {
        this.keyboardMarkups = keyboardMarkups;
        this.userService = userService;
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(CallbackQuery callbackQuery) {
        var chatId = callbackQuery.getFrom().getId().toString();
        var messageId = callbackQuery.getMessage().getMessageId();
        var inlineMessageId = callbackQuery.getInlineMessageId();
        var data = callbackQuery.getData();
        if (data.contains("page")) {
            var page = data.split(" ")[0];
            sendBotMessageService.editInlineMarkup(chatId, messageId, inlineMessageId, keyboardMarkups.getAddMarkup(page));
        } else {
            var token = data.split(" ")[0];
            var user = userService.findUserById(Long.valueOf(chatId)).get();
            if (user.getList().contains(token)) {
                sendBotMessageService.sendMessage(chatId, String.format("You had already added %s to your list", token));
            } else if (user.getList().size() == 5) {
                sendBotMessageService.sendMessage(chatId, TextConstants.FULL_LIST);
            } else {
                user.getList().add(token);
                userService.updateUser(Long.valueOf(chatId), user);
                sendBotMessageService.sendMessage(chatId, String.format("%s was successfully added to your list", token));
            }
        }
    }
}