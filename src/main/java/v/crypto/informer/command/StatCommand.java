package v.crypto.informer.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import v.crypto.informer.service.SendBotMessageService;
import v.crypto.informer.service.impl.TokenService;
import v.crypto.informer.service.impl.UserService;
import v.crypto.informer.util.TextConstants;

import java.util.Collections;

public class StatCommand implements Command {
    private final UserService userService;
    private final TokenService tokenService;
    private final SendBotMessageService sendBotMessageServices;

    public StatCommand(UserService userService, TokenService tokenService, SendBotMessageService sendBotMessageServices) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.sendBotMessageServices = sendBotMessageServices;
    }

    @Override
    public void execute(Update update) {
        var id = update.getMessage().getFrom().getId();
        var optionalUser = userService.findUserById(id);
        if (optionalUser.isPresent()) {
            var user = optionalUser.get();
            var list = user.getList();
            if (list.isEmpty()) {
                sendBotMessageServices.sendMessage(String.valueOf(id), TextConstants.EMPTY_LIST);
            } else {
                Collections.sort(list);
                StringBuilder builder = new StringBuilder();
                for (String tokenName : list) {
                    var token = tokenService.findTokenById(tokenName).get();
                    builder.append(token)
                            .append("\n");
                }
                sendBotMessageServices.sendMessage(String.valueOf(id), builder.toString().trim());
            }
        } else {
            sendBotMessageServices.sendMessage(String.valueOf(id), TextConstants.UNREGISTERED_USER);
        }
    }
}