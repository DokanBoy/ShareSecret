package pw.zakharow.vkbot.command;

import com.petersamokhin.vksdk.core.client.VkApiClient;
import com.petersamokhin.vksdk.core.model.event.IncomingMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pw.zakharow.vkbot.command.impl.CreateSecretCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexey Zakharov
 * @date 26.04.2020
 */
public class CommandManager {

    private static final Logger log = LoggerFactory.getLogger(CommandManager.class);

    private final VkApiClient client;
    private final List<Command> COMMAND_REGISTRY = new ArrayList<>();

    public CommandManager(VkApiClient client) {
        this.client = client;

        COMMAND_REGISTRY.add(new CreateSecretCommand());

        handle();
    }

    private void handle() {
        client.onMessage(event -> {
            COMMAND_REGISTRY.forEach(command -> {
                IncomingMessage message = event.getMessage();

                if (command.getAliases().contains(getArgs(message.getText())[0])) {
                    command.call(event.getMessage(), getArgs(event.getMessage().getText()));
                }
            });
        });
    }

    private String[] getArgs(String sourceText) {
        String[] source = sourceText.split(" ");
        String[] args = new String[source.length - 2];

        for (int i = 2; i < source.length - 3; i++) {
            args[i - 2] = source[i];
        }
        return args;
    }

}
