package pw.zakharov.vkbot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pw.zakharov.vkbot.config.Configuration;
import pw.zakharov.vkbot.config.HoconConfiguration;
import pw.zakharov.vkbot.credentials.BotCredentials;

import java.nio.file.Paths;

/**
 * @author Alexey Zakharov
 * @date 31.05.2020
 */
public final class Launch {

    private static final Logger log = LoggerFactory.getLogger(VkBot.class);

    private static Configuration config;

    public static void main(String[] args) {
        log.info("Welcome back!");

        log.info("Loading configuration file.");
        config = new HoconConfiguration(Paths.get("/"), "config.conf", true);
        BotCredentials credentials = new BotCredentials(config.getHandle().getInt("credentials.groupId"),
                config.getHandle().getString("credentials.accessToken"));
        log.info("Credentials uploaded successfully");

        log.info("Staring launcher for Vk Bot.");
        new VkBot(credentials.groupId(), credentials.accessToken());
        log.info("Good bye.");
    }

    public static Configuration getConfig() {
        return config;
    }

}
