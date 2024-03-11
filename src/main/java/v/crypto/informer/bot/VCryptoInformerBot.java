package v.crypto.informer.bot;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import v.crypto.informer.command.CommandContainer;
import v.crypto.informer.enums.CommandName;
import v.crypto.informer.service.impl.SendBotMessageServiceImpl;
import v.crypto.informer.service.impl.UserService;

@Log4j
@Component
public class VCryptoInformerBot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String botUsername;
    private final CommandContainer commandContainer;

    public VCryptoInformerBot(@Value("${bot.token}") String botToken, UserService userService) {
        super(botToken);
        this.commandContainer = new CommandContainer(userService, new SendBotMessageServiceImpl(this));
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            var message = update.getMessage().getText().trim();
            if (message.startsWith("/")) {
                var commandIdentifier = message.split(" ")[0].toLowerCase();
                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.retrieveCommand(CommandName.NO.getCommandName()).execute(update);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }
}