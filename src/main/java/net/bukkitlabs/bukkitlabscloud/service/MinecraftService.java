package net.bukkitlabs.bukkitlabscloud.service;

import net.bukkitlabs.bukkitlabscloud.server.MinecraftServerFile;
import net.bukkitlabs.bukkitlabscloud.server.MinecraftServerFileDownloader;
import org.jetbrains.annotations.NotNull;
import java.io.File;
import java.io.IOException;

class MinecraftService extends Service {
    private MinecraftServerFile file;
    private Process serverProcess;

    public MinecraftService(@NotNull final MinecraftServerFile file) {
        this.file = file;
    }

    @Override
    public void run() {
        running = true;
        start();

        //while(running){}

        stop();
    }

    public void start() {
        try {
            final String defaultPath = new MinecraftServerFileDownloader().getDefaultPath();
            final String serverFile = file.getMinecraftServerFile().replace(defaultPath, "");
            final ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", serverFile);
            processBuilder.directory(new File(defaultPath));
            serverProcess = processBuilder.start();
        } catch (IOException e) {
            // Logger exception
        }
    }

    public void stop() {
        if (serverProcess != null) {
            serverProcess.destroy();
        }
    }
}
