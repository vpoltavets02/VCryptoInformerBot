package v.crypto.informer.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import v.crypto.informer.keyboards.KeyboardMarkups;
import v.crypto.informer.service.SendBotMessageService;
import v.crypto.informer.service.impl.UserService;
import v.crypto.informer.util.TextConstants;

public class AddCommand implements Command {
    private final KeyboardMarkups keyboardMarkups;
    private final UserService userService;
    private final SendBotMessageService sendBotMessageService;

    public AddCommand(UserService userService, KeyboardMarkups keyboardMarkups, SendBotMessageService sendBotMessageService) {
        this.userService = userService;
        this.keyboardMarkups = keyboardMarkups;
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        var id = update.getMessage().getFrom().getId().toString();
        var optionalUser = userService.findUserById(Long.valueOf(id));
        if (optionalUser.isPresent()) {
            var user = optionalUser.get();
            if (user.getList().size() == 5) {
                sendBotMessageService.sendMessage(id, TextConstants.FULL_LIST);
            } else {
                sendBotMessageService.sendMessageWithKeyboard(id, keyboardMarkups.getAddMarkup("page0"),
                        "Choose token to add:");
            }
        } else {
            sendBotMessageService.sendMessage(id, TextConstants.UNREGISTERED_USER);
        }
    }
}