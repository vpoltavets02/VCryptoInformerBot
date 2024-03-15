package v.crypto.informer.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import v.crypto.informer.keyboards.Keyboards;
import v.crypto.informer.service.SendBotMessageService;
import v.crypto.informer.service.impl.UserService;
import v.crypto.informer.util.TextConstants;

public class AddCommand implements Command {
    private final Keyboards keyboards;
    private final UserService userService;
    private final SendBotMessageService sendBotMessageService;

    public AddCommand(UserService userService, Keyboards keyboards, SendBotMessageService sendBotMessageService) {
        this.userService = userService;
        this.keyboards = keyboards;
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        var id = update.getMessage().getFrom().getId();
        var user = userService.findUserById(id).get();
        if (user.getList().size() == 5) {
            sendBotMessageService.sendMessage(String.valueOf(id), TextConstants.FULL_LIST);
        } else {
            sendBotMessageService.sendMessageWithKeyboard(String.valueOf(id), keyboards.getAddMarkup(), "Choose token to add:");
        }
//        var splitMessage = update.getMessage().getText().split(" ");
//        if (user.getList().size() == 5) {
//            sendBotMessageService.sendMessage(chatId, TextConstants.FULL_LIST);
//        } else {
//            var validationResult = Validator.validateToAdd(user, splitMessage);
//            if (!validationResult.equals("ok")) {
//                sendBotMessageService.sendMessage(chatId, validationResult);
//            } else {
//                var token = splitMessage[1].toUpperCase();
//                user.getList().add(token);
//                userService.updateUser(id, user);
//                sendBotMessageService.sendMessage(chatId, String.format("%s was successfully added to your list", token));
//            }
//        }
    }
}