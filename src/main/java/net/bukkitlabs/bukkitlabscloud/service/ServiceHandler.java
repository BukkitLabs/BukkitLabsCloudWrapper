package net.bukkitlabs.bukkitlabscloud.service;

import net.bukkitlabs.bukkitlabscloud.server.MinecraftServerFile;
import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServiceHandler {
    public final Map<MinecraftServerFile, Service> services;
    private final ExecutorService executorService;

    public ServiceHandler() {
        services = new HashMap<>();
        executorService = Executors.newCachedThreadPool();
    }

    public void startMinecraftServer(@NotNull final MinecraftServerFile file) {
        if (!services.containsKey(file)) {
            final Service service = new MinecraftService(file);
            services.put(file, service);
            executorService.execute(service);
            // Logger started
        } else {
            // Logger already running
        }
    }

    public void stopMinecraftServer(@NotNull final MinecraftServerFile file) {
        if (services.containsKey(file)) {
            final Service service = services.get(file);
            service.stop();
            services.remove(file);
            // Logger stopped
        } else {
            // Logger is not running
        }
    }
}
