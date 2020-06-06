package pw.zakharov.vkbot.command;

import com.petersamokhin.vksdk.core.client.VkApiClient;
import com.petersamokhin.vksdk.core.model.event.IncomingMessage;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pw.zakharov.vkbot.command.impl.CreateSecretCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Alexey Zakharov
 * @date 26.04.2020
 */
public final class CommandManager {

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
            log.debug("Handled message =" + event.getMessage()
                    + ", from id =" + event.getMessage().getFromId()
            );

            COMMAND_REGISTRY.forEach(command -> {
                IncomingMessage message = event.getMessage();
                String name = getCommand(message.getText());
                String[] args = getArgs(message.getText());

                log.debug("Handled command name=" + name
                        + ", from id =" + message.getFromId()
                        + ", message =" + message.getText()
                        + ", where args = " + Arrays.toString(args)
                );

                if (command.getName().equals(name) || command.getAliases().contains(name)) {
                    try { // TODO: избавиться от try-catch
                        command.call(message, args);
                    } catch (ObjectMappingException e) {
                        e.printStackTrace();
                    }
                }
            });
        });
    }

    private String getCommand(String sourceText) {
        return sourceText.split(" ")[0];
    }

    private String[] getArgs(String sourceText) {
        String[] source = sourceText.split(" ");
        String[] args = new String[source.length - 1];

        for (int i = 2; i < source.length - 3; i++) {
            args[i - 2] = source[i];
        }
        return args;
    }

}
