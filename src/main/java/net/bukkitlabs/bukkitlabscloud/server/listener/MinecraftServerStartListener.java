package net.bukkitlabs.bukkitlabscloud.server.listener;

import net.bukkitlabs.bukkitlabscloud.packet.MinecraftServerStartEvent;
import net.bukkitlabs.bukkitlabscloud.service.ServiceHandler;
import net.bukkitlabs.bukkitlabscloudapi.internal.event.Listener;
import net.bukkitlabs.bukkitlabscloudapi.internal.event.PacketCatch;

public class MinecraftServerStartListener implements Listener {

    @PacketCatch(priority = PacketCatch.Priority.LOWEST)
    private void onMinecraftServerStart(final MinecraftServerStartEvent event){
        final ServiceHandler handler = new ServiceHandler();
        handler.startMinecraftServer(event.getMinecraftServerFile());
    }
}
