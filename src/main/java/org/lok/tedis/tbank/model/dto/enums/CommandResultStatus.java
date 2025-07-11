package org.lok.tedis.tbank.model.dto.enums;

import lombok.Getter;

@Getter
public enum CommandResultStatus {
    SUCCESS,
    FAILED;

    public static CommandResultStatus fromString(String status) {
        for (CommandResultStatus commandResultStatus : CommandResultStatus.values()) {
            if (commandResultStatus.toString().equalsIgnoreCase(status)) {
                return commandResultStatus;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + status);
    }
}
