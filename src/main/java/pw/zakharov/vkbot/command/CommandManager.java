package pw.zakharov.vkbot.command;

import com.petersamokhin.vksdk.core.client.VkApiClient;
import com.petersamokhin.vksdk.core.model.event.IncomingMessage;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pw.zakharov.vkbot.command.impl.CreateStoryCommand;
import pw.zakharov.vkbot.manager.StoryService;
import pw.zakharov.vkbot.manager.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by: Alexey Zakharov <alexey@zakharov.pw>
 * Date: 14.07.2020 2:24
 */
public final class CommandManager {

    private static final Logger log = LoggerFactory.getLogger(CommandManager.class);
    private static final List<Command> COMMAND_REGISTRY = new ArrayList<>();

    private final VkApiClient client;
    private final UserService userService;
    private final StoryService storyService;

    public CommandManager(VkApiClient client, UserService userService, StoryService storyService) {
        this.client = client;
        this.userService = userService;
        this.storyService = storyService;

        COMMAND_REGISTRY.add(new CreateStoryCommand(userService, storyService));

        handle();
    }

    private void handle() {
        client.onMessage(event -> {
            COMMAND_REGISTRY.forEach(command -> {
                IncomingMessage message = event.getMessage();
                String name = getCommand(message.getText());
                String[] args = getArgs(message.getText());

                log.debug("Handled command, name=" + name
                        + ", from id=" + message.getFromId()
                        + ", message=" + message.getText()
                        + ", where args=" + Arrays.toString(args)
                );

                if (command.getName().equals(name) || command.getAliases().contains(name)) {
                    try {
                        command.call(message, args);
                    } catch (ObjectMappingException e) {
                        e.printStackTrace();
                    }
                }
            });
        });
    }

    private String getCommand(@NotNull String sourceText) {
        return sourceText.split(" ")[0];
    }

    private String[] getArgs(@NotNull String sourceText) {
        String[] source = sourceText.split(" ");

        int newLength = source.length - 1;
        String[] args = new String[newLength];

        for (int i = 1; i <= newLength; i++) {
            args[i - 1] = source[i];
        }
        return args;
    }

}
