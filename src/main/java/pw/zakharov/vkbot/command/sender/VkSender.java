package pw.zakharov.vkbot.command.sender;

import com.petersamokhin.vksdk.core.model.objects.Message;
import org.jetbrains.annotations.NotNull;
import pw.zakharov.vkbot.VkBot;
import pw.zakharov.vkbot.model.User;

/**
 * Created by: Alexey Zakharov <alexey@zakharov.pw>
 * Date: 16.07.2020 22:14
 */
public class VkSender implements Sender {

    private final String name;
    private final Integer vkId;

    public VkSender(@NotNull String name, @NotNull Integer vkId) {
        this.name = name;
        this.vkId = vkId;
    }

    public static Sender ofUser(@NotNull User user) {
        return new VkSender(user.getName(), user.getVkId());
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public @NotNull Integer getVkId() {
        return vkId;
    }

    @Override
    public void reply(@NotNull String message) {
        new Message()
            .peerId(vkId)
            .text("Hello, world!")
            .sendFrom(VkBot.getClient())
            .execute();
    }

}
