package pw.zakharov.vkbot.command.impl;

import com.petersamokhin.vksdk.core.model.objects.Keyboard;
import org.jetbrains.annotations.NotNull;
import pw.zakharov.vkbot.command.AbstractCommand;
import pw.zakharov.vkbot.command.context.CommandContext;

/**
 * @author Alexey Zakharov
 * @date 06.06.2020
 */
public final class SettingsCommand extends AbstractCommand {

    public SettingsCommand() {
        super("настройки");
    }

    @Override
    protected void execute(@NotNull CommandContext commandContext) {

    }

    @Override
    protected Keyboard getKeyboard() {
        return null;
    }
}