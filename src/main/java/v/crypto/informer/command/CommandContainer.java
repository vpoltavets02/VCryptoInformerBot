package v.crypto.informer.command;

import com.google.common.collect.ImmutableMap;
import v.crypto.informer.keyboards.KeyboardMarkups;
import v.crypto.informer.service.SendBotMessageService;
import v.crypto.informer.command.enums.CommandName;
import v.crypto.informer.service.impl.TokenService;
import v.crypto.informer.service.impl.UserService;

public class CommandContainer {
    private final UnknownCommand unknownCommand;
    private final ImmutableMap<String, Command> commandMap;

    public CommandContainer(UserService userService, TokenService tokenService, SendBotMessageService sendBotMessageService, KeyboardMarkups keyboardMarkups) {
        commandMap = ImmutableMap.<String, Command>builder()
                .put(CommandName.NO.getCommandName(), new NotCommand(sendBotMessageService))
                .put(CommandName.START.getCommandName(), new StartCommand(userService, sendBotMessageService))
                .put(CommandName.PROFILE.getCommandName(), new ProfileCommand(userService, sendBotMessageService))
                .put(CommandName.ADD.getCommandName(), new AddCommand(userService, keyboardMarkups, sendBotMessageService))
                .put(CommandName.REMOVE.getCommandName(), new RemoveCommand(userService, keyboardMarkups, sendBotMessageService))
                .put(CommandName.STAT.getCommandName(), new StatCommand(userService, tokenService, sendBotMessageService))
                .put(CommandName.NOTIFICATIONS.getCommandName(), new NotificationsCommand(userService, sendBotMessageService))
                .put(CommandName.HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }

    public boolean isPresent(String commandIdentifier) {
        return commandMap.containsKey(commandIdentifier);
    }
}