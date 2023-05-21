package net.bukkitlabs.bukkitlabscloud.server.listener;

import net.bukkitlabs.bukkitlabscloud.BukkitLabsCloudWrapper;
import net.bukkitlabs.bukkitlabscloud.packet.MinecraftServerDeleteEvent;
import net.bukkitlabs.bukkitlabscloud.server.MinecraftServerFile;
import net.bukkitlabs.bukkitlabscloud.service.ServiceHandler;
import net.bukkitlabs.bukkitlabscloudapi.internal.console.Logger;
import net.bukkitlabs.bukkitlabscloudapi.internal.event.Listener;
import net.bukkitlabs.bukkitlabscloudapi.internal.event.PacketCatch;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MinecraftServerDeleteListener implements Listener {

    @PacketCatch(priority = PacketCatch.Priority.LOWEST)
    private void onMinecraftServerDelete(MinecraftServerDeleteEvent event) {
        final MinecraftServerFile file = event.getMinecraftServerFile();
        final ServiceHandler handler = new ServiceHandler();
        handler.stopMinecraftServer(file);
        final Pattern pattern = Pattern.compile("/");
        final Matcher matcher = pattern.matcher(file.getMinecraftServerFile());
        String lastElement = "";
        while (matcher.find()) {
            lastElement = matcher.group();
        }
        final Path folder = Paths.get(file.getMinecraftServerFile().replace(lastElement, ""));
        try {
            Files.walk(folder)
                    .sorted((p1, p2) -> -p1.compareTo(p2))
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                        } catch (IOException ignored) {
                        }
                    });
            BukkitLabsCloudWrapper.getLogger().log(Logger.Level.INFO, "Deleted folder " + folder);
        } catch (IOException e) {
            BukkitLabsCloudWrapper.getLogger().log(Logger.Level.ERROR, "Failed to delete folder:" + folder, e);
        }
    }

}
