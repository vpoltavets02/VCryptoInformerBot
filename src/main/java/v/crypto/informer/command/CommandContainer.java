package v.crypto.informer.command;

import com.google.common.collect.ImmutableMap;
import v.crypto.informer.keyboards.Keyboards;
import v.crypto.informer.service.SendBotMessageService;
import v.crypto.informer.command.enums.CommandName;
import v.crypto.informer.service.impl.TokenService;
import v.crypto.informer.service.impl.UserService;

public class CommandContainer {
    private final UnknownCommand unknownCommand;
    private final ImmutableMap<String, Command> commandMap;

    public CommandContainer(UserService userService, TokenService tokenService, SendBotMessageService sendBotMessageService, Keyboards keyboards) {
        commandMap = ImmutableMap.<String, Command>builder()
                .put(CommandName.NO.getCommandName(), new NotCommand(sendBotMessageService))
                .put(CommandName.START.getCommandName(), new StartCommand(userService, sendBotMessageService))
                .put(CommandName.PROFILE.getCommandName(), new ProfileCommand(userService, sendBotMessageService))
                .put(CommandName.ADD.getCommandName(), new AddCommand(userService, keyboards, sendBotMessageService))
                .put(CommandName.REMOVE.getCommandName(), new RemoveCommand(userService, keyboards, sendBotMessageService))
                .put(CommandName.STAT.getCommandName(), new StatCommand(userService, tokenService, sendBotMessageService))
                .put(CommandName.NOTIFICATIONS.getCommandName(), new NotificationsCommand(userService, sendBotMessageService))
                .put(CommandName.HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}