package v.crypto.informer.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import v.crypto.informer.data.TokenData;
import v.crypto.informer.service.SendBotMessageService;
import v.crypto.informer.service.impl.UserService;
import v.crypto.informer.util.TextConstants;

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
        if (user.getList().size() == 5) {
            sendBotMessageService.sendMessage(String.valueOf(id), TextConstants.FULL_LIST);
        } else {
            sendBotMessageService.sendMessage(String.valueOf(id),
                    String.format("Chose token you want to add to your list: \n\n<b>%s</b>", TextConstants.TOKEN_LIST));
        }
    }
}