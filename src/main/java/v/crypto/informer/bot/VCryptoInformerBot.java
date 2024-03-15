package v.crypto.informer.bot;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import v.crypto.informer.callback.CallbackContainer;
import v.crypto.informer.command.CommandContainer;
import v.crypto.informer.command.enums.CommandName;
import v.crypto.informer.keyboards.Keyboards;
import v.crypto.informer.service.impl.SendBotMessageServiceImpl;
import v.crypto.informer.service.impl.TokenService;
import v.crypto.informer.service.impl.UserService;

@Log4j
@Component
public class VCryptoInformerBot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String botUsername;
    private final CommandContainer commandContainer;
    private final CallbackContainer callbackContainer;

    public VCryptoInformerBot(@Value("${bot.token}") String botToken, UserService userService, TokenService tokenService, Keyboards keyboards) {
        super(botToken);
        this.callbackContainer = new CallbackContainer(userService, new SendBotMessageServiceImpl(this));
        this.commandContainer = new CommandContainer(userService, tokenService, new SendBotMessageServiceImpl(this), keyboards);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if ((!update.hasMessage() || !update.getMessage().hasText()) && !update.hasCallbackQuery()) {
            commandContainer.retrieveCommand(CommandName.NO.getCommandName()).execute(update);
        } else if (update.hasMessage() && update.getMessage().hasText()) {
            var message = update.getMessage().getText().trim();
            if (message.startsWith("/")) {
                var commandIdentifier = message.split(" ")[0].toLowerCase();
                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.retrieveCommand(CommandName.NO.getCommandName()).execute(update);
            }
        } else if (update.hasCallbackQuery()) {
            var callback = update.getCallbackQuery();
            var callbackIdentifier = callback.getData().split(" ")[1];
            callbackContainer.retrieveCallback(callbackIdentifier).execute(callback);
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }
}