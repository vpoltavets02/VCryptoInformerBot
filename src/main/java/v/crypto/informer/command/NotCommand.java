package v.crypto.informer.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import v.crypto.informer.service.SendBotMessageService;

public class NotCommand implements Command {
    private final SendBotMessageService sendBotMessageService;

    public NotCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        String text = "I'm sorry, but I support only commands from command menu.\n" +
                "To see the whole list of available commands use ❓help";
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), text);
    }
}