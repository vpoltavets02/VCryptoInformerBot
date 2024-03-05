package v.crypto.informer.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import v.crypto.informer.data.TokenData;
import v.crypto.informer.service.SendBotMessageService;

public class StatCommand implements Command {
    private final SendBotMessageService sendBotMessageServices;

    public StatCommand(SendBotMessageService sendBotMessageServices) {
        this.sendBotMessageServices = sendBotMessageServices;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageServices.sendMessage(update.getMessage().getChatId().toString(), TokenData.getListInfo());
    }
}
