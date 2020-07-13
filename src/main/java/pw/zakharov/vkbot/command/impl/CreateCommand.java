package pw.zakharov.vkbot.command.impl;

import com.google.common.collect.Lists;
import com.petersamokhin.vksdk.core.model.objects.Keyboard;
import com.petersamokhin.vksdk.core.model.objects.Message;
import org.jetbrains.annotations.NotNull;
import pw.zakharov.vkbot.Launch;
import pw.zakharov.vkbot.command.AbstractCommand;
import pw.zakharov.vkbot.command.context.CommandContext;
import pw.zakharov.vkbot.manager.StoryService;
import pw.zakharov.vkbot.manager.UserService;
import pw.zakharov.vkbot.model.Story;
import pw.zakharov.vkbot.model.User;

import java.util.Arrays;
import java.util.List;

/**
 * Created by: Alexey Zakharov <alexey@zakharov.pw>
 * Date: 14.07.2020 2:24
 */

public final class CreateCommand extends AbstractCommand {

    private static final int MIN_WORDS = Launch.getConfig().getNode("settings").getNode("story_min_words").getInt();
    private static final int MAX_WORDS = Launch.getConfig().getNode("settings").getNode("story_max_words").getInt();

    private final UserService userService;
    private final StoryService storyService;

    public CreateCommand(UserService userService, StoryService storyService) {
        super("добавить");

        this.userService = userService;
        this.storyService = storyService;

        this.commandUsage = getUsage() + getName() + " <твоя история>";
    }

    @Override
    protected void execute(@NotNull CommandContext commandContext) {
        User user;
        if (userService.containsUserByVkId(commandContext.getSource().getFromId())) {
            user = userService.createUser("Empty", commandContext.getSource().getFromId(), List.of());
        } else {
            user = null;
            // TODO: user = userService.getUserByVkId(Long);
        }

        if (commandContext.args().size() < MIN_WORDS) {
            new Message()
                .peerId(commandContext.getSource().getPeerId())
                .text("""
                    Минимальный размер истории {min_words} слов.
                    Размер твоей истории: {now_words}"""
                    .replace("{min_words}", String.valueOf(MIN_WORDS))
                    .replace("{now_words}", String.valueOf(commandContext.args().size()))
                )
                .sendFrom(client)
                .execute();
            return;
        }

        if (commandContext.args().size() > MAX_WORDS) {
            new Message()
                .peerId(commandContext.getSource().getPeerId())
                .text("""
                    Максимальный размер истории {max_words} слов.
                    Размер твоей истории: {now_words}"""
                    .replace("{max_words}", String.valueOf(MAX_WORDS))
                    .replace("{now_words}", String.valueOf(commandContext.args().size()))
                )
                .sendFrom(client)
                .execute();
            return;
        }

        String storyText = Arrays.toString(commandContext.args().toArray(new String[]{}));
        storyText = storyText.substring(1, storyText.length() - 1).replace(",", "");
        Story story = storyService.createStory(user, storyText);

        new Message()
            .peerId(commandContext.getSource().getPeerId())
            .text(("""
                Новая история создана! 
                Текст: {text} 

                Как только появится первый лайк, тебе сразу придет оповещение.""")
                .replace("{text}", storyText)
            )
            .keyboard(getKeyboard())
            .sendFrom(client)
            .execute();
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
