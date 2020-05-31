package pw.zakharow.vkbot.command;

import com.google.common.collect.ImmutableList;
import com.petersamokhin.vksdk.core.model.event.IncomingMessage;

/**
 * @author Alexey Zakharov
 * @date 30.04.2020
 */
public interface Command {

    String getName();

    ImmutableList<String> getAliases();

    String getUsage();

    boolean isNeedReply();

    void call(IncomingMessage incomingMessage, String... args);

}
