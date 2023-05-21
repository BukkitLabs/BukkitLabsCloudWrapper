package net.bukkitlabs.bukkitlabscloud.server;

import net.bukkitlabs.bukkitlabscloud.BukkitLabsCloudWrapper;
import net.bukkitlabs.bukkitlabscloud.packet.*;
import net.bukkitlabs.bukkitlabscloudapi.internal.event.PacketCannotBeProcessedException;
import org.jetbrains.annotations.NotNull;

public class MinecraftServerHandler extends MinecraftServer {
    @Override
    public void start(@NotNull MinecraftServerFile file) {
        try {
            BukkitLabsCloudWrapper.getPacketHandler().call(new MinecraftServerStartEvent(file));
        } catch (PacketCannotBeProcessedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void stop(@NotNull MinecraftServerFile file) {
        try {
            BukkitLabsCloudWrapper.getPacketHandler().call(new MinecraftServerStopEvent(file));
        } catch (PacketCannotBeProcessedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void restart(@NotNull MinecraftServerFile file) {
        try {
            BukkitLabsCloudWrapper.getPacketHandler().call(new MinecraftServerRestartEvent(file));
        } catch (PacketCannotBeProcessedException e) {
            throw new RuntimeException(e);
        }
    }

    public void create(@NotNull final MinecraftServer.Type type, @NotNull final String version, @NotNull final String name) {
        try {
            BukkitLabsCloudWrapper.getPacketHandler().call(new MinecraftServerCreateEvent(type,version,name));
        } catch (PacketCannotBeProcessedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(@NotNull MinecraftServerFile file) {
        try {
            BukkitLabsCloudWrapper.getPacketHandler().call(new MinecraftServerDeleteEvent(file));
        } catch (PacketCannotBeProcessedException e) {
            throw new RuntimeException(e);
        }
    }
}
