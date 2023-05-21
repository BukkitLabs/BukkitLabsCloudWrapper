package net.bukkitlabs.bukkitlabscloud.exception;

import org.jetbrains.annotations.NotNull;

public class InvalidMinecraftServerFileException extends Exception {
    public InvalidMinecraftServerFileException(@NotNull final String message) {
        super(message);
    }
}
