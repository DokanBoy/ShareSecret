package pw.zakharow.vkbot.command.impl;

import com.petersamokhin.vksdk.core.model.objects.Keyboard;
import com.petersamokhin.vksdk.core.model.objects.Message;
import pw.zakharow.vkbot.command.AbstractCommand;
import pw.zakharow.vkbot.command.context.CommandContext;
import pw.zakharow.vkbot.model.SecretModel;

import java.util.LinkedList;

/**
 * @author Alexey Zakharov
 * @date 30.04.2020
 */
public class CreateSecretCommand extends AbstractCommand {

    public CreateSecretCommand() {
        super("добавить");

        this.commandUsage = getUsage() + getName() + " <ваш секрет>";
    }

    @Override
    public void execute(CommandContext commandContext) {
        int id = 1; // TODO
        new SecretModel(id, commandContext.getMessage(), 0, 0).id();

        new Message()
                .peerId(commandContext.getSource().getPeerId())
                .text("""
                        Секрет создан #{id}!
                        Текст: {text} 
                        Как только появится первый лайк, тебе сразу придет оповещение.
                        """
                        .replace("{text}", commandContext.getMessage())
                        .replace("{id}", String.valueOf(id))
                )
                /*.setKeyboard() TODO*/
                .sendFrom(client)
                .execute();
    }

}
