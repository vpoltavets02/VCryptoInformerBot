package v.crypto.informer.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import v.crypto.informer.data.TokenData;
import v.crypto.informer.service.SendBotMessageService;
import v.crypto.informer.service.impl.UserService;
import v.crypto.informer.util.TextConstants;

import java.util.Collections;

public class StatCommand implements Command {
    private final UserService userService;
    private final SendBotMessageService sendBotMessageServices;

    public StatCommand(UserService userService, SendBotMessageService sendBotMessageServices) {
        this.userService = userService;
        this.sendBotMessageServices = sendBotMessageServices;
    }

    @Override
    public void execute(Update update) {
        StringBuilder builder = new StringBuilder();
        var id = update.getMessage().getFrom().getId();
        var user = userService.findUserById(id).get();
        var list = user.getList();
        if (list.isEmpty()) {
            sendBotMessageServices.sendMessage(String.valueOf(id), TextConstants.EMPTY_LIST);
        } else {
            Collections.sort(list);
            for (String tokenName : list) {
                var token = TokenData.tokenInfoMap.get(tokenName);
                builder.append(token.toString())
                        .append("\n");
            }
            sendBotMessageServices.sendMessage(String.valueOf(id), builder.toString().trim());
        }
    }
}