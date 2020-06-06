package pw.zakharov.vkbot.command.impl;

import com.google.common.collect.Lists;
import com.petersamokhin.vksdk.core.model.objects.Keyboard;
import com.petersamokhin.vksdk.core.model.objects.Message;
import org.jetbrains.annotations.NotNull;
import pw.zakharov.vkbot.Launch;
import pw.zakharov.vkbot.command.AbstractCommand;
import pw.zakharov.vkbot.command.context.CommandContext;

import java.util.Arrays;
import java.util.List;

/**
 * @author Alexey Zakharov
 * @date 30.04.2020
 */
public final class CreateCommand extends AbstractCommand {

    private static final int MIN_WORDS = Launch.getConfig().getNode("settings").getNode("history_min_words").getInt();

    private static int secretId = 0;

    public CreateCommand() {
        super("добавить");

        this.commandUsage = getUsage() + getName() + " <твоя история>";
    }

    @Override
    protected void execute(@NotNull CommandContext commandContext) {
        if (commandContext.args().size() < MIN_WORDS) {
            new Message()
                    .peerId(commandContext.getSource().getPeerId())
                    .text(("Минимальный размер истории {min_words} слов." + '\n'
                            + "Размер твоей истории: {now_words} ")
                            .replace("{min_words}", String.valueOf(MIN_WORDS))
                            .replace("{now_words}", String.valueOf(commandContext.args().size()))
                    )
                    .sendFrom(client)
                    .execute();
            return;
        }
        String history = Arrays.toString(commandContext.args().toArray(new String[]{}));
        history = history.substring(1, history.length() - 1).replace(",", "");

        //new Secret(User.of(commandContext.getSource().getFromId()), history);
        new Message()
                .peerId(commandContext.getSource().getPeerId())
                .text(("История #{id} создана! " + '\n'
                        + "Текст: {text} " + '\n'
                        + '\n'
                        + "Как только появится первый лайк, тебе сразу придет оповещение.")
                        .replace("{text}", history)
                        .replace("{id}", String.valueOf(secretId))
                )
                .keyboard(getKeyboard())
                .sendFrom(client)
                .execute();

        ++secretId;
    }

    // TODO: Refactor for init this method
    @Override
    protected Keyboard getKeyboard() {
        List<List<Keyboard.Button>> keyboardButtons = Lists.newArrayList();

        List<Keyboard.Button> firstRow = Lists.newArrayList();

        firstRow.add(new Keyboard.Button(
                new Keyboard.Button.Action(Keyboard.Button.Action.Type.TEXT, "Лайк",
                        null, null, null, null, null),
                Keyboard.Button.Color.POSITIVE
        ));
        firstRow.add(new Keyboard.Button(
                new Keyboard.Button.Action(Keyboard.Button.Action.Type.TEXT, "Дизлайк",
                        null, null, null, null, null),
                Keyboard.Button.Color.NEGATIVE
        ));
        firstRow.add(new Keyboard.Button(
                new Keyboard.Button.Action(Keyboard.Button.Action.Type.TEXT, "Жалоба",
                        null, null, null, null, null),
                Keyboard.Button.Color.SECONDARY
        ));
        firstRow.add(new Keyboard.Button(
                new Keyboard.Button.Action(Keyboard.Button.Action.Type.TEXT, "\uD83D\uDCA4",
                        null, null, null, null, null),
                Keyboard.Button.Color.SECONDARY
        ));

        keyboardButtons.add(firstRow);

        return new Keyboard(keyboardButtons, true, false, null);
    }

}
