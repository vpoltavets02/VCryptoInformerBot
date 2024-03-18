package v.crypto.informer.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import v.crypto.informer.keyboards.KeyboardMarkups;
import v.crypto.informer.service.SendBotMessageService;
import v.crypto.informer.service.impl.UserService;
import v.crypto.informer.util.TextConstants;


public class RemoveCommand implements Command {
    private final UserService userService;
    private final KeyboardMarkups keyboardMarkups;
    private final SendBotMessageService sendBotMessageService;

    public RemoveCommand(UserService userService, KeyboardMarkups keyboardMarkups, SendBotMessageService sendBotMessageService) {
        this.userService = userService;
        this.keyboardMarkups = keyboardMarkups;
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        var id = update.getMessage().getFrom().getId();
        var optionalUser = userService.findUserById(id);
        if (optionalUser.isPresent()) {
            var user = optionalUser.get();
            var chatId = String.valueOf(id);
            var list = user.getList();
            if (list.isEmpty()) {
                sendBotMessageService.sendMessage(chatId, "You can't delete any token from list because it's " +
                        "empty. Use ➕add command to add tokens to list");
            } else {
                sendBotMessageService.sendMessageWithKeyboard(chatId, keyboardMarkups.getRemoveMarkup(list),
                        "Choose token to remove: ");
            }
        } else {
            sendBotMessageService.sendMessage(String.valueOf(id), TextConstants.UNREGISTERED_USER);
        }
    }
}