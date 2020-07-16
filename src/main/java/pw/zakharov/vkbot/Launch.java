package pw.zakharov.vkbot;

import lombok.AllArgsConstructor;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pw.zakharov.vkbot.utils.ConfigUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by: Alexey Zakharov <alexey@zakharov.pw>
 * Date: 14.07.2020 1:34
 */
public final class Launch {

    private static final Logger log = LoggerFactory.getLogger(Launch.class);

    private static ConfigurationNode config;

    private Launch() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

    public static void main(String[] args) throws IOException {
        log.info("Welcome back!");

        log.info("Loading configuration file.");
        if (Files.notExists(Paths.get("config.conf"))) {
            ConfigUtil.saveDefaultConfig();
            log.info("Default config saved.");
        }

        log.debug("Config file exist? " + Files.exists(Paths.get("config.conf")));
        HoconConfigurationLoader loader = HoconConfigurationLoader.builder()
                .setPath(Paths.get("config.conf"))
                .build();
        try {
            config = loader.load();
            log.info("Configuration file loaded successfully!");
        } catch (final IOException e) {
            log.error("An error occurred while loading this configuration.", e);
            System.exit(2);
        }

        log.info("Staring launcher for Vk Bot.");
        VkBot.start(
                config.getNode("credentials").getNode("groupId").getInt(),
                config.getNode("credentials").getNode("accessToken").getString()
        );
        log.info("Good bye.");
    }

    public static ConfigurationNode getConfig() {
        return config;
    }

}
