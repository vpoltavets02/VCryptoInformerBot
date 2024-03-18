package v.crypto.informer.command.enums;

import lombok.Getter;

@Getter
public enum CommandName {
    NO("no"),
    START("/start"),
    ADD("➕Add"),
    REMOVE("➖Remove"),
    STAT("\uD83D\uDCCAStatistic"),
    NOTIFICATIONS("\uD83D\uDD14Notifications"),
    PROFILE("\uD83D\uDC64Profile"),
    HELP("❓Help");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }
}