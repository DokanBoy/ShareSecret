package pw.zakharow.vkbot;

import com.petersamokhin.vksdk.core.client.VkApiClient;
import com.petersamokhin.vksdk.core.http.HttpClient;
import com.petersamokhin.vksdk.core.model.VkSettings;
import com.petersamokhin.vksdk.http.VkOkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pw.zakharow.vkbot.command.CommandManager;

/**
 * @author Alexey Zakharov
 * @date 31.05.2020
 */
public final class VkBot {

    private static final Logger log = LoggerFactory.getLogger(VkBot.class);

    private static VkApiClient client;

    public VkBot(int groupId, String accessToken) {
        HttpClient vkHttpClient = new VkOkHttpClient();
        client = new VkApiClient(groupId, accessToken, VkApiClient.Type.Community, new VkSettings(vkHttpClient));
        new CommandManager(client);

        client.startLongPolling(true);
    }

    public static VkApiClient getClient() {
        return client;
    }

}
