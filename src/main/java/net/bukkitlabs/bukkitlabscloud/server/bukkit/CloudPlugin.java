package net.bukkitlabs.bukkitlabscloud.server.bukkit;

import net.bukkitlabs.bukkitlabscloud.plugin.BukkitLabsCloudPlugin;
import org.jetbrains.annotations.NotNull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class CloudPlugin {
    private String pluginPath;


    public CloudPlugin(@NotNull final String pluginPath){
        setPluginPath(pluginPath);
    }

    @NotNull
    private String getPluginPath() {
        return pluginPath;
    }

    private void setPluginPath(@NotNull final String pluginPath) {
        this.pluginPath=pluginPath;
    }

    @NotNull
    public BukkitLabsCloudPlugin getInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, MalformedURLException {
        final URLClassLoader classLoader = new URLClassLoader(new URL[]{new URL("file:" + pluginPath)});
        final Class<?> pluginClass = classLoader.loadClass("net.bukkitlabs.bukkitlabscloud.plugin.BukkitLabsCloudPlugin");
        final Object pluginInstance = pluginClass.newInstance();
        final Method getInstanceMethod = pluginClass.getDeclaredMethod("getInstance");
        final BukkitLabsCloudPlugin plugin = (BukkitLabsCloudPlugin) getInstanceMethod.invoke(pluginInstance);
        return plugin;
    }

}
