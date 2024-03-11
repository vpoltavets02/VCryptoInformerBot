package v.crypto.informer.enums;

import lombok.Getter;

@Getter
public enum CommandName {
    NO("no"),
    START("/start"),
    ADD("/add"),
    REMOVE("/remove"),
    STAT("/stat"),
    ALL_TOKENS("/all_tokens"),
    HELP("/help");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }
}