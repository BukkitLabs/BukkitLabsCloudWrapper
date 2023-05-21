package net.bukkitlabs.bukkitlabscloud.server.listener;

import net.bukkitlabs.bukkitlabscloud.packet.MinecraftServerRestartEvent;
import net.bukkitlabs.bukkitlabscloud.service.ServiceHandler;
import net.bukkitlabs.bukkitlabscloudapi.internal.event.Listener;
import net.bukkitlabs.bukkitlabscloudapi.internal.event.PacketCatch;

public class MinecraftServerRestartListener implements Listener {

    @PacketCatch(priority = PacketCatch.Priority.LOWEST)
    private void onMinecraftServerRestart(final MinecraftServerRestartEvent event){
        final ServiceHandler handler = new ServiceHandler();
        handler.stopMinecraftServer(event.getMinecraftServerFile());
        handler.startMinecraftServer(event.getMinecraftServerFile());
    }
}
