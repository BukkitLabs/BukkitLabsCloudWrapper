package net.bukkitlabs.bukkitlabscloud.server.exeption;

import org.jetbrains.annotations.NotNull;

public class InvalidJarFileException extends Exception{
    public InvalidJarFileException(@NotNull final String message) {
        super(message);
    }
}
