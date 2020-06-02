package pw.zakharow.vkbot;

import pw.zakharow.vkbot.config.Configuration;
import pw.zakharow.vkbot.config.HoconConfiguration;
import pw.zakharow.vkbot.credentials.BotCredentials;

import java.nio.file.Paths;

/**
 * @author Alexey Zakharov
 * @date 31.05.2020
 */
public final class Launch {

    private static Configuration config;

    public static void main(String[] args) {
        config = new HoconConfiguration(Paths.get("/"), "config.conf", true);

        BotCredentials credentials = new BotCredentials(config.getHandle().getInt("credentials.groupId"),
                config.getHandle().getString("credentials.accessToken"));

        new VkBot(credentials.groupId(), credentials.accessToken());
    }

    public static Configuration getConfig() {
        return config;
    }

}
