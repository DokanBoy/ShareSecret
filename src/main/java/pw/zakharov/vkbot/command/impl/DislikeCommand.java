package pw.zakharov.vkbot.command.impl;

import com.petersamokhin.vksdk.core.model.objects.Keyboard;
import org.jetbrains.annotations.NotNull;
import pw.zakharov.vkbot.command.AbstractCommand;
import pw.zakharov.vkbot.command.context.CommandContext;

/**
 * Created by: Alexey Zakharov <alexey@zakharov.pw>
 * Date: 14.07.2020 2:24
 */
public final class DislikeCommand extends AbstractCommand {

    public DislikeCommand() {
        super("дизлайк");
    }

    @Override
    protected void execute(@NotNull CommandContext commandContext) {

    }

    @Override
    protected Keyboard getKeyboard() {
        return null;
    }
}
