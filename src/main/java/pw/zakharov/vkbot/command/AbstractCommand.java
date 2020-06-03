package pw.zakharov.vkbot.command;

import com.google.common.collect.ImmutableList;
import com.petersamokhin.vksdk.core.client.VkApiClient;
import com.petersamokhin.vksdk.core.model.event.IncomingMessage;
import com.petersamokhin.vksdk.core.model.objects.Keyboard;
import org.jetbrains.annotations.NotNull;
import pw.zakharov.vkbot.Launch;
import pw.zakharov.vkbot.VkBot;
import pw.zakharov.vkbot.command.context.CommandContext;
import pw.zakharov.vkbot.command.context.VkCommandContext;
import pw.zakharov.vkbot.config.Configuration;

import java.util.function.Consumer;

/**
 * @author Alexey Zakharov
 * @date 31.05.2020
 */
public abstract class AbstractCommand implements Command {

    protected static final VkApiClient client = VkBot.getClient();

    private static final Configuration config = Launch.getConfig();

    private final String name;
    private final String[] aliases;
    private final int needArgs;
    private final int level;
    private final boolean reply;

    protected String commandUsage = "Вы неправильно используйте команду!\nИспользуйте: ";

    /**
     * @param name     Имя команды
     * @param needArgs Кол-во нужных аргументов
     * @param level    Права для ее выполнения:
     *                 0 - Пользователь,
     *                 1 - Админ
     */
    protected AbstractCommand(@NotNull String name, int needArgs, int level, boolean reply, String... aliases) {
        this.name = name;
        this.aliases = aliases;
        this.needArgs = needArgs;
        this.level = level;
        this.reply = reply;
    }

    protected AbstractCommand(@NotNull String name, int level, String... aliases) {
        this.name = name;
        this.aliases = aliases;
        this.needArgs = 0;
        this.level = level;
        this.reply = false;
    }

    protected AbstractCommand(@NotNull String name, boolean reply, String... aliases) {
        this.name = name;
        this.aliases = aliases;
        this.needArgs = 0;
        this.level = 0;
        this.reply = reply;
    }

    protected AbstractCommand(@NotNull String... aliases) {
        this.name = aliases[0];
        this.aliases = aliases;
        this.needArgs = 0;
        this.level = 0;
        this.reply = false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUsage() {
        return commandUsage;
    }

    @Override
    public boolean isNeedReply() {
        return reply;
    }

    @Override
    public void onReply(Consumer<CommandContext> contextConsumer) {
        // TODO
    }

    @Override
    public ImmutableList<String> getAliases() {
        return ImmutableList.copyOf(aliases);
    }

    @Override
    public void call(@NotNull IncomingMessage incomingMessage, String... args) {
        if (needArgs >= 0)
            if (args.length <= needArgs)
                return;

        if (level == 1 && !config.getIntegerList("admins").contains(incomingMessage.getFromId()))
            return;

        // TODO: if user not contains in users repo then create.
        execute(new VkCommandContext(incomingMessage, args));
    }

    protected abstract void execute(@NotNull CommandContext commandContext);

    protected abstract Keyboard initKeyboard();

}
