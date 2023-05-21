package net.bukkitlabs.bukkitlabscloud;

import net.bukkitlabs.bukkitlabscloud.packet.ServerInitializeEvent;
import net.bukkitlabs.bukkitlabscloud.packet.ServerShutdownEvent;
import net.bukkitlabs.bukkitlabscloud.server.listener.*;
import net.bukkitlabs.bukkitlabscloudapi.internal.console.CommandHandler;
import net.bukkitlabs.bukkitlabscloudapi.internal.console.Logger;
import net.bukkitlabs.bukkitlabscloudapi.internal.event.Listener;
import net.bukkitlabs.bukkitlabscloudapi.internal.event.PacketCannotBeProcessedException;
import net.bukkitlabs.bukkitlabscloudapi.internal.event.PacketCatch;
import net.bukkitlabs.bukkitlabscloudapi.internal.event.PacketHandler;
import org.jetbrains.annotations.NotNull;

public class BukkitLabsCloudWrapper implements Listener {

    private static Logger logger;
    private static PacketHandler packetHandler;
    private static CommandHandler commandHandler;

    public BukkitLabsCloudWrapper() {
        setPacketHandler(new PacketHandler());
        setLogger(new Logger());
        registerListeners();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                getPacketHandler().call(new ServerShutdownEvent());
            } catch (PacketCannotBeProcessedException exception) {
                getLogger().log(Logger.Level.ERROR, "Event call failed: " + exception);
            }
        }));

        try {
            getPacketHandler().call(new ServerInitializeEvent());
        } catch (PacketCannotBeProcessedException exception) {
            getLogger().log(Logger.Level.ERROR, "Event call failed: " + exception);
        }

        getCommandHandler().startListening();
    }

    private void registerListeners(){
        getPacketHandler().registerListener(getLogger());
        getPacketHandler().registerListener(this);
        getPacketHandler().registerListener(new MinecraftServerCreateListener());
        getPacketHandler().registerListener(new MinecraftServerStartListener());
        getPacketHandler().registerListener(new MinecraftServerStopListener());
        getPacketHandler().registerListener(new MinecraftServerRestartListener());
        getPacketHandler().registerListener(new MinecraftServerDeleteListener());
    }

    @NotNull
    public static Logger getLogger() {
        return logger;
    }

    private static void setLogger(@NotNull final Logger logger) {
        BukkitLabsCloudWrapper.logger = logger;
    }

    @NotNull
    public static PacketHandler getPacketHandler() {
        return packetHandler;
    }

    private static void setPacketHandler(@NotNull final PacketHandler packetHandler) {
        BukkitLabsCloudWrapper.packetHandler = packetHandler;
    }

    @NotNull
    public static CommandHandler getCommandHandler() {
        return commandHandler;
    }

    private static void setCommandHandler(@NotNull final CommandHandler commandHandler) {
        BukkitLabsCloudWrapper.commandHandler = commandHandler;
    }

    @PacketCatch
    private void onServerInitialization(final ServerInitializeEvent event) {
        getLogger().log(Logger.Level.INFO, "Starting BukkitLabsCloud...");
    }

    @PacketCatch
    private void onServerShutdown(final ServerShutdownEvent event) {
        getLogger().log(Logger.Level.INFO, "Goodbye...");
    }
}