package v.crypto.informer.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import v.crypto.informer.service.SendBotMessageService;
import v.crypto.informer.service.impl.UserService;
import v.crypto.informer.util.TextConstants;
import v.crypto.informer.util.Validator;

public class AddCommand implements Command {
    private final UserService userService;
    private final SendBotMessageService sendBotMessageService;

    public AddCommand(UserService userService, SendBotMessageService sendBotMessageService) {
        this.userService = userService;
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        var id = update.getMessage().getFrom().getId();
        var user = userService.findUserById(id).get();
        var chatId = String.valueOf(id);
        var splitMessage = update.getMessage().getText().split(" ");
        if (user.getList().size() == 5) {
            sendBotMessageService.sendMessage(chatId, TextConstants.FULL_LIST);
        } else {
            var validationResult = Validator.validateToAdd(user, splitMessage);
            if (!validationResult.equals("ok")) {
                sendBotMessageService.sendMessage(chatId, validationResult);
            } else {
                var token = splitMessage[1].toUpperCase();
                user.getList().add(token);
                userService.updateUser(id, user);
                sendBotMessageService.sendMessage(chatId, String.format("%s was successfully added to your list", token));
            }
        }
    }
}