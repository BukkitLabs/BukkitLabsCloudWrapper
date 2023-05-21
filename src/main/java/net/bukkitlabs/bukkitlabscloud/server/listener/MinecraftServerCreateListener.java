package net.bukkitlabs.bukkitlabscloud.server.listener;

import net.bukkitlabs.bukkitlabscloud.exception.InvalidMinecraftServerTypeException;
import net.bukkitlabs.bukkitlabscloud.packet.MinecraftServerCreateEvent;
import net.bukkitlabs.bukkitlabscloud.server.MinecraftServerFileDownloader;
import net.bukkitlabs.bukkitlabscloudapi.internal.event.Listener;
import net.bukkitlabs.bukkitlabscloudapi.internal.event.PacketCatch;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class MinecraftServerCreateListener implements Listener {

    @PacketCatch(priority = PacketCatch.Priority.LOWEST)
    private void onMinecraftServerCreate(@NotNull final MinecraftServerCreateEvent event){
        MinecraftServerFileDownloader downloader = new MinecraftServerFileDownloader();
        downloader.setType(event.getType());
        downloader.setVersion(event.getVersion());
        downloader.setServerName(event.getName());
        try {
            downloader.download();
            Path resourcePath = Path.of(Objects.requireNonNull(MinecraftServerCreateListener.class.getClassLoader().getResource("eula.txt")).toURI());

            Files.copy(resourcePath, Path.of(event.getPath()), StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Datei erfolgreich kopiert.");
        } catch (InvalidMinecraftServerTypeException | IOException | NullPointerException | IllegalArgumentException | SecurityException | java.net.URISyntaxException e) {
            System.out.println("Fehler beim Kopieren der Datei: " + e.getMessage());
        }
    }
}
