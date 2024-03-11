package v.crypto.informer.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import v.crypto.informer.service.SendBotMessageService;
import v.crypto.informer.util.TextConstants;

public class AllTokensCommand implements Command {
    private final SendBotMessageService sendBotMessageService;

    public AllTokensCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        var chatId = update.getMessage().getChatId().toString();
        sendBotMessageService.sendMessage(chatId, String.format("<b>%s</b>", TextConstants.TOKEN_LIST));
    }
}