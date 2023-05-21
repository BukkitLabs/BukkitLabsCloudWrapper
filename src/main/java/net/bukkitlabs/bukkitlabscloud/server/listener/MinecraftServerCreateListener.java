package net.bukkitlabs.bukkitlabscloud.server.listener;

import net.bukkitlabs.bukkitlabscloud.exception.InvalidMinecraftServerTypeException;
import net.bukkitlabs.bukkitlabscloud.packet.MinecraftServerCreateEvent;
import net.bukkitlabs.bukkitlabscloud.server.MinecraftServerFileDownloader;
import net.bukkitlabs.bukkitlabscloudapi.internal.event.Listener;
import net.bukkitlabs.bukkitlabscloudapi.internal.event.PacketCatch;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;

public class MinecraftServerCreateListener implements Listener {

    @PacketCatch(priority = PacketCatch.Priority.LOWEST)
    private void onMinecraftServerCreate(@NotNull final MinecraftServerCreateEvent event){
        MinecraftServerFileDownloader downloader = new MinecraftServerFileDownloader();
        downloader.setType(event.getType());
        downloader.setVersion(event.getVersion());
        downloader.setServerName(event.getName());
        try {
            downloader.download();
        } catch (InvalidMinecraftServerTypeException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
