package pw.zakharow.vkbot.command;

import com.google.common.collect.ImmutableList;
import com.petersamokhin.vksdk.core.model.event.IncomingMessage;
import org.jetbrains.annotations.NotNull;
import pw.zakharow.vkbot.command.context.CommandContext;

import java.util.function.Consumer;

/**
 * @author Alexey Zakharov
 * @date 30.04.2020
 */
public interface Command {

    String getName();

    ImmutableList<String> getAliases();

    String getUsage();

    boolean isNeedReply();

    void onReply(Consumer<CommandContext> contextConsumer);

    void call(@NotNull IncomingMessage incomingMessage, String... args);

}
