package v.crypto.informer.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import v.crypto.informer.service.SendBotMessageService;

public class UnknownCommand implements Command {
    private final SendBotMessageService sendBotMessageService;

    public UnknownCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        String text = "I don't know this command.\n" +
                "You can use command /help to get the whole list of available commands.";
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), text);
    }
}
