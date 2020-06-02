package pw.zakharow.vkbot.command.context;

import com.google.common.collect.ImmutableList;
import com.petersamokhin.vksdk.core.model.event.IncomingMessage;

/**
 * @author Alexey Zakharov
 * @date 31.05.2020
 */
public final class VkCommandContext implements CommandContext {

    private final ImmutableList<String> args;
    private final IncomingMessage source;

    public VkCommandContext(IncomingMessage incomingMessage, String... args) {
        this.args = ImmutableList.copyOf(args);
        this.source = incomingMessage;
    }

    @Override
    public ImmutableList<String> args() {
        return args;
    }

    @Override
    public String arg(int index) {
        return args.get(index);
    }

    @Override
    public String getMessage() {
        return source.getText();
    }

    @Override
    public IncomingMessage getSource() {
        return source;
    }

    @Override
    public String toString() {
        return "VkCommandContext{" + "args=" + args +
                ", command sender=" + source.getFromId() +
                ", source message=" + source.getText() +
                '}';
    }

}
