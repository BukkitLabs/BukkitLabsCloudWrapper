package net.bukkitlabs.bukkitlabscloud.exception;

import org.jetbrains.annotations.NotNull;

public class InvalidMinecraftServerTypeException extends Exception {
    public InvalidMinecraftServerTypeException(@NotNull final String message) {
        super(message);
    }
}
