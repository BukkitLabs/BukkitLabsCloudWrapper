package net.bukkitlabs.bukkitlabscloud.server;

import org.jetbrains.annotations.NotNull;

public abstract class MinecraftServer {
    public abstract void start(@NotNull final MinecraftServerFile file);

    public abstract void stop(@NotNull final MinecraftServerFile file);

    public abstract void restart(@NotNull final MinecraftServerFile file);

    public abstract void create(@NotNull final MinecraftServer.Type type, @NotNull final String version, @NotNull final String name);

    public abstract void delete(@NotNull final MinecraftServerFile file);


    public enum Type {
        BUKKIT("Bukkit"),
        SPIGOT("Spigot"),
        PAPER("Paper");

        Type(@NotNull final String name) {
            this.name = name;
        }

        private final String name;

        @NotNull
        public String getName() {
            return name;
        }
    }

}
