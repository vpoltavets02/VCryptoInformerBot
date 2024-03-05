package v.crypto.informer.command;

import com.google.common.collect.ImmutableMap;
import v.crypto.informer.service.SendBotMessageService;

public class CommandContainer {
    private final UnknownCommand unknownCommand;
    private final ImmutableMap<String, Command> commandMap;

    public CommandContainer(SendBotMessageService sendBotMessageService) {
        commandMap = ImmutableMap.<String, Command>builder()
                .put(CommandName.NO.getCommandName(), new NotCommand(sendBotMessageService))
                .put(CommandName.START.getCommandName(), new StartCommand(sendBotMessageService))
                .put(CommandName.STAT.getCommandName(), new StatCommand(sendBotMessageService))
                .put(CommandName.HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}