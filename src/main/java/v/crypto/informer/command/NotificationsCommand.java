package v.crypto.informer.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import v.crypto.informer.service.SendBotMessageService;
import v.crypto.informer.service.impl.UserService;
import v.crypto.informer.util.TextConstants;

public class NotificationsCommand implements Command {
    private final UserService userService;
    private final SendBotMessageService sendBotMessageService;

    public NotificationsCommand(UserService userService, SendBotMessageService sendBotMessageService) {
        this.userService = userService;
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        var id = update.getMessage().getFrom().getId();
        var optionalUser = userService.findUserById(id);
        if (optionalUser.isPresent()) {
            var user = optionalUser.get();
            user.setNotifications(!user.getNotifications());
            userService.updateUser(id, user);
            String message = String.format("Your notifications status was updated. You %s get notifications", user.getNotifications() ? "will" : "won't");
            sendBotMessageService.sendMessage(String.valueOf(id), message);
        } else {
            sendBotMessageService.sendMessage(String.valueOf(id), TextConstants.UNREGISTERED_USER);
        }
    }
}