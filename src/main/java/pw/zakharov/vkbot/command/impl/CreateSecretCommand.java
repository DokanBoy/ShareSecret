package pw.zakharov.vkbot.command.impl;

import com.google.common.collect.Lists;
import com.petersamokhin.vksdk.core.model.objects.Keyboard;
import com.petersamokhin.vksdk.core.model.objects.Message;
import org.jetbrains.annotations.NotNull;
import pw.zakharov.vkbot.command.AbstractCommand;
import pw.zakharov.vkbot.command.context.CommandContext;
import pw.zakharov.vkbot.persistance.User;
import pw.zakharov.vkbot.persistance.Secret;

import java.util.List;

/**
 * @author Alexey Zakharov
 * @date 30.04.2020
 */
public final class CreateSecretCommand extends AbstractCommand {

    private final Keyboard keyboard;

    public CreateSecretCommand() {
        super("добавить");

        this.commandUsage = getUsage() + getName() + " <ваш секрет>";
        keyboard = initKeyboard();
    }

    @Override
    protected void execute(@NotNull CommandContext commandContext) {
        int id = 1; // TODO
        new Secret(User.of(id), commandContext.getMessage());

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
                .keyboard(keyboard)
                .sendFrom(client)
                .execute();
    }

    // TODO: Refactor for init this method
    @Override
    protected Keyboard initKeyboard() {
        List<List<Keyboard.Button>> keyboardButtons = Lists.newArrayList();


        List<Keyboard.Button> firstRow = Lists.newArrayList();

        firstRow.add(new Keyboard.Button(
                new Keyboard.Button.Action(Keyboard.Button.Action.Type.TEXT, "Key #1",
                        null, null, null, null, null),
                Keyboard.Button.Color.POSITIVE
        ));
        firstRow.add(new Keyboard.Button(
                new Keyboard.Button.Action(Keyboard.Button.Action.Type.TEXT, "Key #2",
                        null, null, null, null, null),
                Keyboard.Button.Color.PRIMARY
        ));
        firstRow.add(new Keyboard.Button(
                new Keyboard.Button.Action(Keyboard.Button.Action.Type.TEXT, "Key #3",
                        null, null, null, null, null),
                Keyboard.Button.Color.NEGATIVE
        ));

        keyboardButtons.add(firstRow);


        List<Keyboard.Button> secondRow = Lists.newArrayList();

        secondRow.add(new Keyboard.Button(
                new Keyboard.Button.Action(Keyboard.Button.Action.Type.TEXT, "Key #4",
                        null, null, null, null, null),
                Keyboard.Button.Color.SECONDARY
        ));
        secondRow.add(new Keyboard.Button(
                new Keyboard.Button.Action(Keyboard.Button.Action.Type.TEXT, "Key #5",
                        null, null, null, null, null),
                Keyboard.Button.Color.POSITIVE
        ));
        secondRow.add(new Keyboard.Button(
                new Keyboard.Button.Action(Keyboard.Button.Action.Type.TEXT, "Key #6",
                        null, null, null, null, null),
                Keyboard.Button.Color.NEGATIVE
        ));

        keyboardButtons.add(secondRow);

        return new Keyboard(keyboardButtons, true, false, null);
    }

}
