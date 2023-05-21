package net.bukkitlabs.bukkitlabscloud.server;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.bukkitlabs.bukkitlabscloud.exception.InvalidMinecraftServerTypeException;
import org.jetbrains.annotations.NotNull;
import java.io.*;
import java.net.URL;

public class MinecraftServerFileDownloader {

    private MinecraftServer.Type type;
    private String version;
    private final String defaultPath = "/wrapper/server/";
    private String name;
    private String path;

    public MinecraftServerFileDownloader() {
    }

    @NotNull
    public MinecraftServer.Type getType() {
        return type;
    }

    @NotNull
    public String getVersion() {
        return version;
    }

    @NotNull
    public String getServerName() {
        return name;
    }

    @NotNull
    public String getServerPath() {
        this.path = defaultPath + name;
        return path;
    }

    @NotNull
    public String getDefaultPath() {
        return defaultPath;
    }

    public void setType(@NotNull final MinecraftServer.Type type) {
        this.type = type;
    }

    public void setVersion(@NotNull final String version) {
        this.version = version;
    }

    public void setServerName(@NotNull final String name) {
        this.name = name;
    }

    public void download() throws InvalidMinecraftServerTypeException, IOException {
        if (getVersion() == null) throw new NullPointerException("Version can not be null");
        if (getType() == null) throw new NullPointerException("Type can not be null");
        if (getServerName() == null) throw new NullPointerException("Server name can not be null");
        final String downloadUrl = generateDownloadURL();
        final BufferedInputStream bufferedInputStream = new BufferedInputStream(new URL(downloadUrl).openStream());
        final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(getServerPath()));
        final byte[] dataBuffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = bufferedInputStream.read(dataBuffer, 0, 1024)) != -1) {
            bufferedOutputStream.write(dataBuffer, 0, bytesRead);
        }
    }

    @NotNull
    private String generateDownloadURL() throws InvalidMinecraftServerTypeException, IOException {
        switch (getType().getName()) {
            case "Bukkit":
                return "https://cdn.getbukkit.org/craftbukkit/craftbukkit-" + getVersion() + ".jar";
            case "Spigot":
                return "https://cdn.getbukkit.org/spigot/spigot-" + getVersion() + ".jar";
            case "Paper":
                return "https://papermc.io/api/v2/projects/paper/versions/" + getVersion() + "/builds/" + getLatestBuild() + "/downloads/paper-" + getVersion() + "-" + getLatestBuild() + ".jar";
            default:
                throw new InvalidMinecraftServerTypeException("Invalid minecraft server type: " + getType().getName());
        }
    }

    private int getLatestBuild() throws IOException {
        final String apiUrl = "https://papermc.io/api/v2/projects/paper/versions/" + getVersion();
        final URL url = new URL(apiUrl);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            return jsonObject.getAsJsonArray("builds").get(0).getAsInt();
        }
    }
}
