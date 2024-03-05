package v.crypto.informer.command;

public enum CommandName {
    NO("no"),
    START("/start"),
    ADD("/add"),
    REMOVE("/remove"),
    STAT("/stat"),
    HELP("/help");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}