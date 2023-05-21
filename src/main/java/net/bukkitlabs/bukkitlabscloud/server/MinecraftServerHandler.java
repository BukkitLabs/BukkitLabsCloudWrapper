package net.bukkitlabs.bukkitlabscloud.server;

import net.bukkitlabs.bukkitlabscloud.server.exeption.InvalidMinecraftServerTypeException;
import net.bukkitlabs.bukkitlabscloud.service.ServiceHandler;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;

public class MinecraftServerHandler extends MinecraftServer {
    @Override
    public void start(@NotNull MinecraftServerFile file) {
        final ServiceHandler handler = new ServiceHandler();
        handler.startMinecraftServer(file);
    }

    @Override
    public void stop(@NotNull MinecraftServerFile file) {
        final ServiceHandler handler = new ServiceHandler();
        handler.stopMinecraftServer(file);
    }

    @Override
    public void restart(@NotNull MinecraftServerFile file) {
        final ServiceHandler handler = new ServiceHandler();
        handler.stopMinecraftServer(file);
        handler.startMinecraftServer(file);
    }

    public void create(@NotNull final MinecraftServer.Type type, @NotNull final String version, @NotNull final String name) {
        MinecraftServerFileDownloader downloader = new MinecraftServerFileDownloader();
        downloader.setType(type);
        downloader.setVersion(version);
        downloader.setServerName(name);
        try {
            downloader.download();
        } catch (InvalidMinecraftServerTypeException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(@NotNull MinecraftServerFile file) {

    }
}
