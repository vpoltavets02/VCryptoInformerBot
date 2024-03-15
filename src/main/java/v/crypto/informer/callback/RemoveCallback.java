package v.crypto.informer.callback;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import v.crypto.informer.service.SendBotMessageService;
import v.crypto.informer.service.impl.UserService;

public class RemoveCallback implements Callback {
    private final UserService userService;
    private final SendBotMessageService sendBotMessageService;

    public RemoveCallback(UserService userService, SendBotMessageService sendBotMessageService) {
        this.userService = userService;
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(CallbackQuery callbackQuery) {
        var chatId = callbackQuery.getFrom().getId().toString();
        var token = callbackQuery.getData().split(" ")[0];
        var user = userService.findUserById(Long.valueOf(chatId)).get();
        var list = user.getList();
        if (list.isEmpty()) {
            sendBotMessageService.sendMessage(chatId, "You can't delete any token from list because it's empty");
        } else if (!list.contains(token)) {
            sendBotMessageService.sendMessage(chatId, String.format("You can't delete %s because you haven't this token in your list", token));
        } else {
            list.remove(token);
            user.setList(list);
            userService.updateUser(Long.valueOf(chatId), user);
            sendBotMessageService.sendMessage(chatId, String.format("%s was successfully removed from your list", token));
        }
    }
}