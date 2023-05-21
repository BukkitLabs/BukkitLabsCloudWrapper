package net.bukkitlabs.bukkitlabscloud.packet;

import net.bukkitlabs.bukkitlabscloud.server.MinecraftServer;
import net.bukkitlabs.bukkitlabscloudapi.internal.event.Cancelable;
import net.bukkitlabs.bukkitlabscloudapi.internal.event.Packet;
import org.jetbrains.annotations.NotNull;

public class MinecraftServerCreateEvent extends Packet implements Cancelable {

    private MinecraftServer.Type type;
    private String version;
    private final String defaultPath = "/wrapper/server/"; // path: /wrapper/server/[name]
    private String name;
    private String path;
    private boolean canceled=false;

    public MinecraftServerCreateEvent(@NotNull final MinecraftServer.Type type,@NotNull final String version,@NotNull final String name) {
        this.type = type;
        this.version = version;
        this.name = name;
    }

    @NotNull
    public MinecraftServer.Type getType() {
        return type;
    }

    public void setType(@NotNull final MinecraftServer.Type type) {
        this.type = type;
    }

    @NotNull
    public String getVersion() {
        return version;
    }

    public void setVersion(@NotNull final String version) {
        this.version = version;
    }

    @NotNull
    public String getDefaultPath() {
        return defaultPath;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull final String name) {
        this.name = name;
    }

    @NotNull
    public String getPath() {
        path=defaultPath+name;
        return path;
    }

    @Override
    public boolean isCanceled() {
        return canceled;
    }

    @Override
    public void setCanceled(boolean canceled) {
        this.canceled=canceled;
    }
}
