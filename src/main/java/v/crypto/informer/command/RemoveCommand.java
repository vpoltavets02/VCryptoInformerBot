package v.crypto.informer.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import v.crypto.informer.keyboards.Keyboards;
import v.crypto.informer.service.SendBotMessageService;
import v.crypto.informer.service.impl.UserService;


public class RemoveCommand implements Command {
    private final UserService userService;
    private final Keyboards keyboards;
    private final SendBotMessageService sendBotMessageService;

    public RemoveCommand(UserService userService, Keyboards keyboards, SendBotMessageService sendBotMessageService) {
        this.userService = userService;
        this.keyboards = keyboards;
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        var id = update.getMessage().getFrom().getId();
        var user = userService.findUserById(id).get();
        var chatId = String.valueOf(id);
        var list = user.getList();
        if (list.isEmpty()) {
            sendBotMessageService.sendMessage(chatId, "You can't delete any token from list because it's empty");
        } else {
            sendBotMessageService.sendMessageWithKeyboard(chatId, keyboards.getRemoveMarkup(list), "Choose token to remove: ");
//            var splitMessage = update.getMessage().getText().split(" ");
//            var validationResult = Validator.validateToRemove(user, splitMessage);
//            if (!validationResult.equals("ok"))
//                sendBotMessageService.sendMessage(chatId, validationResult);
//            else {
//                var token = splitMessage[1].toUpperCase();
//                user.getList().remove(token);
//                userService.updateUser(id, user);
//                sendBotMessageService.sendMessage(chatId, String.format("%s was successfully removed from your list", token));
//            }
        }
    }
}