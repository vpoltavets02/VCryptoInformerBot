package v.crypto.informer.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import v.crypto.informer.model.User;
import v.crypto.informer.service.SendBotMessageService;
import v.crypto.informer.service.impl.UserService;
import v.crypto.informer.util.TextConstants;

import java.util.ArrayList;
import java.util.Optional;

public class StartCommand implements Command {
    private final UserService userService;
    private final SendBotMessageService sendBotMessageService;

    public StartCommand(UserService userService, SendBotMessageService sendBotMessageService) {
        this.userService = userService;
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        var id = update.getMessage().getFrom().getId();
        Optional<User> optionalUser = userService.findUserById(id);
        if (optionalUser.isEmpty())
            userService.save(new User(id, new ArrayList<>()));
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), TextConstants.START_MESSAGE);
    }
}
