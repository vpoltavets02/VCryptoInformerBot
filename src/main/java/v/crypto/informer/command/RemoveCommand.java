package v.crypto.informer.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import v.crypto.informer.service.SendBotMessageService;
import v.crypto.informer.service.impl.UserService;
import v.crypto.informer.util.Validator;

public class RemoveCommand implements Command {
    private final UserService userService;
    private final SendBotMessageService sendBotMessageService;

    public RemoveCommand(UserService userService, SendBotMessageService sendBotMessageService) {
        this.userService = userService;
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        var id = update.getMessage().getFrom().getId();
        var user = userService.findUserById(id).get();
        var chatId = String.valueOf(id);
        if (user.getList().isEmpty()) {
            sendBotMessageService.sendMessage(chatId, "You can't delete any token from list because it's empty");
        } else {
            var splitMessage = update.getMessage().getText().split(" ");
            var validationResult = Validator.validateToRemove(user, splitMessage);
            if (!validationResult.equals("ok"))
                sendBotMessageService.sendMessage(chatId, validationResult);
            else {
                var token = splitMessage[1].toUpperCase();
                user.getList().remove(token);
                userService.updateUser(id, user);
                sendBotMessageService.sendMessage(chatId, String.format("%s was successfully removed from your list", token));
            }
        }
    }
}