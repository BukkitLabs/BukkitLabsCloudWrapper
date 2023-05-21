package net.bukkitlabs.bukkitlabscloud.configuration;

import net.bukkitlabs.bukkitlabscloudapi.internal.config.ConfigCreator;
import net.bukkitlabs.bukkitlabscloudapi.internal.config.Configuration;
import net.bukkitlabs.bukkitlabscloudapi.internal.config.ConfigurationProvider;
import net.bukkitlabs.bukkitlabscloudapi.internal.config.JsonConfiguration;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

public class WrapperConfigurationHandler {
    private final ConfigurationProvider provider= Objects.requireNonNull(ConfigurationProvider.getProvider(JsonConfiguration.class));
    private File wrapperConfigurationFile;
    private Configuration wrapperConfiguration;

    public WrapperConfigurationHandler() throws IOException {
        this.wrapperConfigurationFile = Paths.get(".", "wrapper/config.json").toFile();
        if (!wrapperConfigurationFile.exists()) {
            final ConfigCreator creator = new ConfigCreator(Paths.get("."));
            this.wrapperConfigurationFile = creator.copyDefaultFile(Paths.get("config.json"));
        }
        this.reloadConfigurations();
    }

    public void reloadConfigurations() throws IOException {
        this.wrapperConfiguration = this.provider.load(this.wrapperConfigurationFile);
    }

    public void saveConfigurations() throws IOException {
        this.provider.save(this.wrapperConfiguration, this.wrapperConfigurationFile);
    }

    public Configuration getGeneralConfiguration() {
        return wrapperConfiguration;
    }


}
