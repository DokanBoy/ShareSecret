package pw.zakharow.vkbot.command.context;

import com.google.common.collect.ImmutableList;
import com.petersamokhin.vksdk.core.model.event.IncomingMessage;

/**
 * @author Alexey Zakharov
 * @date 31.05.2020
 */
public interface CommandContext {

    ImmutableList<String> args();

    String arg(int index);

    String getMessage();

    IncomingMessage getSource();
}
