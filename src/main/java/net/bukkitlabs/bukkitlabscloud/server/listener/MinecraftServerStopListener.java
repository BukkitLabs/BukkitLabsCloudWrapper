package net.bukkitlabs.bukkitlabscloud.server.listener;

import net.bukkitlabs.bukkitlabscloud.packet.MinecraftServerStopEvent;
import net.bukkitlabs.bukkitlabscloud.service.ServiceHandler;
import net.bukkitlabs.bukkitlabscloudapi.internal.event.Listener;
import net.bukkitlabs.bukkitlabscloudapi.internal.event.PacketCatch;

public class MinecraftServerStopListener implements Listener {

    @PacketCatch(priority = PacketCatch.Priority.LOWEST)
    private void onMinecraftServerStop(final MinecraftServerStopEvent event){
        final ServiceHandler handler = new ServiceHandler();
        handler.stopMinecraftServer(event.getMinecraftServerFile());
    }
}
