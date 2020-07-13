package pw.zakharov.vkbot;

import com.petersamokhin.vksdk.core.callback.EventCallback;
import com.petersamokhin.vksdk.core.client.VkApiClient;
import com.petersamokhin.vksdk.core.http.HttpClient;
import com.petersamokhin.vksdk.core.model.VkSettings;
import com.petersamokhin.vksdk.core.model.event.MessageNew;
import com.petersamokhin.vksdk.http.VkOkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pw.zakharov.vkbot.command.CommandManager;

/**
 * @author Alexey Zakharov
 * @since 31.05.2020
 */
public final class VkBot {

    private static final Logger log = LoggerFactory.getLogger(VkBot.class);

    private static VkApiClient client;

    public VkBot(int groupId, String accessToken) {
        log.debug("Group Id: " + groupId);
        log.debug("Access token: " + accessToken);

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
