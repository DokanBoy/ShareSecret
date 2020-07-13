package pw.zakharov.vkbot.command.context;

import com.google.common.collect.ImmutableList;
import com.petersamokhin.vksdk.core.model.event.IncomingMessage;
import org.jetbrains.annotations.NotNull;

/**
 * Created by: Alexey Zakharov <alexey@zakharov.pw>
 * Date: 14.07.2020 2:24
 */

public interface CommandContext {

    ImmutableList<String> args();

    String arg(int index);

    String getMessage();

    IncomingMessage getSource();
}
