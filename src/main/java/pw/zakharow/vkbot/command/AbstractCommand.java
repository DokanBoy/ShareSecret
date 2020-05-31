package pw.zakharow.vkbot.command;

import com.google.common.collect.ImmutableList;
import com.petersamokhin.vksdk.core.client.VkApiClient;
import com.petersamokhin.vksdk.core.model.event.IncomingMessage;
import pw.zakharow.vkbot.Launch;
import pw.zakharow.vkbot.VkBot;
import pw.zakharow.vkbot.command.context.CommandContext;
import pw.zakharow.vkbot.command.context.VkCommandContext;

/**
 * @author Alexey Zakharov
 * @date 31.05.2020
 */
public abstract class AbstractCommand implements Command {

    protected static final VkApiClient client = VkBot.getClient();

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
    public AbstractCommand(String name, int needArgs, int level, boolean reply, String... aliases) {
        this.name = name;
        this.aliases = aliases;
        this.needArgs = needArgs;
        this.level = level;
        this.reply = reply;
    }

    public AbstractCommand(String name, int level, String... aliases) {
        this.name = name;
        this.aliases = aliases;
        this.needArgs = 0;
        this.level = level;
        this.reply = false;
    }

    public AbstractCommand(String name, boolean reply, String... aliases) {
        this.name = name;
        this.aliases = aliases;
        this.needArgs = 0;
        this.level = 0;
        this.reply = reply;
    }

    public AbstractCommand(String... aliases) {
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
    public ImmutableList<String> getAliases() {
        return ImmutableList.copyOf(aliases);
    }

    @Override
    public void call(IncomingMessage incomingMessage, String... args) {
        if (needArgs >= 0)
            if (args.length <= needArgs)
                return;

        if (level == 1 && !Launch.getConfig().getIntegerList("admins").contains(incomingMessage.getFromId()))
            return;

        execute(new VkCommandContext(incomingMessage, args));
    }

    public abstract void execute(CommandContext commandContext);
}
