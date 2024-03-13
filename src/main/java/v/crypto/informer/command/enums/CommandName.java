package v.crypto.informer.command.enums;

import lombok.Getter;

@Getter
public enum CommandName {
    NO("no"),
    START("/start"),
    ADD("/add"),
    REMOVE("/remove"),
    STAT("/stat"),
    ALL_TOKENS("/all_tokens"),
    NOTIFICATIONS("/notifications"),
    PROFILE("/profile"),
    HELP("/help");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }
}