package v.crypto.informer.bot;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import v.crypto.informer.callback.CallbackContainer;
import v.crypto.informer.command.CommandContainer;
import v.crypto.informer.command.enums.CommandName;
import v.crypto.informer.keyboards.KeyboardMarkups;
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

    public VCryptoInformerBot(@Value("${bot.token}") String botToken,
                              UserService userService, TokenService tokenService, KeyboardMarkups keyboardMarkups) {
        super(botToken);
        this.callbackContainer = new CallbackContainer(keyboardMarkups, userService,
                new SendBotMessageServiceImpl(keyboardMarkups, this));
        this.commandContainer = new CommandContainer(userService, tokenService,
                new SendBotMessageServiceImpl(keyboardMarkups, this), keyboardMarkups);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            var commandIdentifier = update.getMessage().getText();
            if (commandContainer.isPresent(commandIdentifier))
                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            else
                commandContainer.retrieveCommand(CommandName.NO.getCommandName()).execute(update);
        } else if (update.hasCallbackQuery()) {
            var callback = update.getCallbackQuery();
            var callbackIdentifier = callback.getData().split(" ")[1];
            callbackContainer.retrieveCallback(callbackIdentifier).execute(callback);
        } else {
            commandContainer.retrieveCommand(CommandName.NO.getCommandName()).execute(update);
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }
}