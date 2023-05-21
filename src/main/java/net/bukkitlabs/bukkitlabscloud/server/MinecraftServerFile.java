package net.bukkitlabs.bukkitlabscloud.server;

import net.bukkitlabs.bukkitlabscloud.exception.InvalidMinecraftServerFileException;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class MinecraftServerFile {

    private String minecraftServerFile;

    public MinecraftServerFile(@NotNull final String minecraftServerFile) throws InvalidMinecraftServerFileException {
        if (!minecraftServerFile.endsWith(".jar")) {
            throw new InvalidMinecraftServerFileException("Invalid jar file: " + minecraftServerFile);
        }
        if (!new File(minecraftServerFile).exists() || !new File(minecraftServerFile).isFile()) {
            throw new InvalidMinecraftServerFileException("File does not exist or is not a regular file: " + minecraftServerFile);
        }
        if (!isMinecraftServerJar(minecraftServerFile)) {
            throw new InvalidMinecraftServerFileException("Invalid minecraft server jar file: " + minecraftServerFile);
        }
        this.minecraftServerFile = minecraftServerFile;
    }

    @NotNull
    public String getMinecraftServerFile() {
        return minecraftServerFile;
    }

    private boolean isMinecraftServerJar(@NotNull final String filePath) {
        try {
            JarFile jarFile = new JarFile(filePath);
            if (containsEntry(jarFile, "net/minecraft/server/MinecraftServer.class") ||
                    containsEntry(jarFile, "net/minecraft/server/dedicated/DedicatedServer.class") ||
                    containsEntry(jarFile, "net/minecraft/server/Main.class") ||
                    containsEntry(jarFile, "server.properties")) {
                return true;
            }
            jarFile.close();
        } catch (IOException ignored) {
        }
        return false;
    }

    private boolean containsEntry(@NotNull final JarFile jarFile, @NotNull final String entryName) {
        JarEntry entry = jarFile.getJarEntry(entryName);
        return entry != null;
    }
}
