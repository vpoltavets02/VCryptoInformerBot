package v.crypto.informer.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import v.crypto.informer.service.SendBotMessageService;
import v.crypto.informer.service.impl.UserService;
import v.crypto.informer.util.TextConstants;

public class ProfileCommand implements Command {
    private final UserService userService;
    private final SendBotMessageService sendBotMessageService;

    public ProfileCommand(UserService userService, SendBotMessageService sendBotMessageService) {
        this.userService = userService;
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        var id = update.getMessage().getFrom().getId();
        var optionalUser = userService.findUserById(id);
        if (optionalUser.isPresent()) {
            var user = optionalUser.get();
            var username = update.getMessage().getFrom().getUserName();
            String message = String.format("<b>Username:</b> %s\n<b>Notifications:</b> turned %s\n<b>Token list:</b> %s",
                    username, user.getNotifications() ? "on" : "off", user.displayList());
            sendBotMessageService.sendMessage(String.valueOf(id), message);
        } else {
            sendBotMessageService.sendMessage(String.valueOf(id), TextConstants.UNREGISTERED_USER);
        }
    }
}