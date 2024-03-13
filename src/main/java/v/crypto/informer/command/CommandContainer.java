package v.crypto.informer.command;

import com.google.common.collect.ImmutableMap;
import v.crypto.informer.service.SendBotMessageService;
import v.crypto.informer.command.enums.CommandName;
import v.crypto.informer.service.impl.UserService;

public class CommandContainer {
    private final UnknownCommand unknownCommand;
    private final ImmutableMap<String, Command> commandMap;

    public CommandContainer(UserService userService, SendBotMessageService sendBotMessageService) {
        commandMap = ImmutableMap.<String, Command>builder()
                .put(CommandName.NO.getCommandName(), new NotCommand(sendBotMessageService))
                .put(CommandName.START.getCommandName(), new StartCommand(userService, sendBotMessageService))
                .put(CommandName.PROFILE.getCommandName(), new ProfileCommand(userService, sendBotMessageService))
                .put(CommandName.ADD.getCommandName(), new AddCommand(userService, sendBotMessageService))
                .put(CommandName.REMOVE.getCommandName(), new RemoveCommand(userService, sendBotMessageService))
                .put(CommandName.STAT.getCommandName(), new StatCommand(userService, sendBotMessageService))
                .put(CommandName.ALL_TOKENS.getCommandName(), new AllTokensCommand(sendBotMessageService))
                .put(CommandName.NOTIFICATIONS.getCommandName(), new NotificationsCommand(userService, sendBotMessageService))
                .put(CommandName.HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}