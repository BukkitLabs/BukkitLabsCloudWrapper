package net.bukkitlabs.bukkitlabscloud.packet;

import net.bukkitlabs.bukkitlabscloud.server.MinecraftServerFile;
import net.bukkitlabs.bukkitlabscloudapi.internal.event.Cancelable;
import net.bukkitlabs.bukkitlabscloudapi.internal.event.Packet;
import org.jetbrains.annotations.NotNull;

public class MinecraftServerStartEvent extends Packet implements Cancelable {
    private MinecraftServerFile minecraftServerFile;
    private boolean canceled=false;

    public MinecraftServerStartEvent(@NotNull final MinecraftServerFile minecraftServerFile) {
        this.minecraftServerFile = minecraftServerFile;
    }

    @NotNull
    public MinecraftServerFile getMinecraftServerFile() {
        return minecraftServerFile;
    }

    public void setMinecraftServerFile(@NotNull final MinecraftServerFile minecraftServerFile) {
        this.minecraftServerFile = minecraftServerFile;
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
