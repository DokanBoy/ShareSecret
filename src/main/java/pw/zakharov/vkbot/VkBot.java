package pw.zakharov.vkbot;

import com.petersamokhin.vksdk.core.client.VkApiClient;
import com.petersamokhin.vksdk.core.http.HttpClient;
import com.petersamokhin.vksdk.core.model.VkSettings;
import com.petersamokhin.vksdk.http.VkOkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pw.zakharov.vkbot.command.CommandManager;

/**
 * Created by: Alexey Zakharov <alexey@zakharov.pw>
 * Date: 14.07.2020 1:34
 */
public final class VkBot {

    private static final Logger log = LoggerFactory.getLogger(VkBot.class);

    private static VkApiClient client;

    private VkBot() throws IllegalAccessException {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

    public static void start(int groupId, String accessToken) {
        log.info("Starting Http client.");
        HttpClient vkHttpClient = new VkOkHttpClient();

        log.info("Starting Vk Api client.");
        client = new VkApiClient(groupId, accessToken, VkApiClient.Type.Community, new VkSettings(vkHttpClient));

        log.info("Initialization CommandManager.");
        new CommandManager(client);

        log.info("Starting LongPoll.");
        client.startLongPolling();
    }

    public static VkApiClient getClient() {
        return client;
    }

}
