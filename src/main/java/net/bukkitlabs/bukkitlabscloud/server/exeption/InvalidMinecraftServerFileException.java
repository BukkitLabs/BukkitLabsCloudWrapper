package net.bukkitlabs.bukkitlabscloud.server.exeption;

import org.jetbrains.annotations.NotNull;

public class InvalidMinecraftServerFileException extends Exception {
    public InvalidMinecraftServerFileException(@NotNull final String message) {
        super(message);
    }
}
