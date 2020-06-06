package pw.zakharov.vkbot.command;

import com.google.common.collect.ImmutableList;
import com.google.common.reflect.TypeToken;
import com.petersamokhin.vksdk.core.client.VkApiClient;
import com.petersamokhin.vksdk.core.model.event.IncomingMessage;
import com.petersamokhin.vksdk.core.model.objects.Keyboard;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pw.zakharov.vkbot.Launch;
import pw.zakharov.vkbot.VkBot;
import pw.zakharov.vkbot.command.context.CommandContext;
import pw.zakharov.vkbot.command.context.VkCommandContext;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author Alexey Zakharov
 * @date 31.05.2020
 */
public abstract class AbstractCommand implements Command {

    protected static final VkApiClient client = VkBot.getClient();

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final String name;
    private final String[] aliases;
    private final int minArgs;
    private final int level;
    private final boolean reply;

    protected String commandUsage = "Вы неправильно используйте команду!\nИспользуйте: ";

    /**
     * @param name    Имя команды
     * @param minArgs Кол-во нужных аргументов
     * @param level   Права для ее выполнения:
     *                0 - Пользователь,
     *                1 - Админ
     */
    protected AbstractCommand(@NotNull String name, int minArgs, int level, String... aliases) {
        this.name = name;
        this.aliases = aliases;
        this.minArgs = minArgs;
        this.level = level;
        this.reply = false;
    }

    protected AbstractCommand(int minArgs, @NotNull String... aliases) {
        this.name = aliases[0];
        this.aliases = aliases;
        this.minArgs = minArgs;
        this.level = 0;
        this.reply = false;
    }

    protected AbstractCommand(@NotNull String... aliases) {
        this.name = aliases[0];
        this.aliases = aliases;
        this.minArgs = 0;
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
        if (minArgs > 0)
            if (args.length <= minArgs)
                return;

        if (level > 0) {
            try {
                List<Integer> admins = Launch.getConfig().getList(TypeToken.of(Integer.class));
                if (admins.contains(incomingMessage.getFromId()))
                    return;
            } catch (ObjectMappingException e) {
                log.error("Error while get admins list", e);
            }
        }

        // TODO: if user not contains in users repo then create.
        execute(new VkCommandContext(incomingMessage, args));
    }

    protected abstract void execute(@NotNull CommandContext commandContext);

    protected abstract Keyboard getKeyboard();

}
