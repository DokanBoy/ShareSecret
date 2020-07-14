package pw.zakharov.vkbot.command.context;

import com.petersamokhin.vksdk.core.model.event.IncomingMessage;
import org.jetbrains.annotations.NotNull;
import pw.zakharov.vkbot.model.Story;
import pw.zakharov.vkbot.model.User;

import javax.annotation.Nullable;

/**
 * Created by: Alexey Zakharov <alexey@zakharov.pw>
 * Date: 14.07.2020 13:24
 */

public class VkUserContext implements UserContext {

    private final User user;
    private Story lastSentStory;
    private IncomingMessage previousMessage;

    public VkUserContext(User user, IncomingMessage previousMessage, Story lastSentStory) {
        this.user = user;
        this.previousMessage = previousMessage;
        this.lastSentStory = lastSentStory;
    }

    @Override
    @NotNull
    public User getContextOwner() {
        return user;
    }

    @Override
    @Nullable
    public Story getLastSentStory() {
        return lastSentStory;
    }

    @Override
    public void setLastSentStory(@Nullable Story story) {
        this.lastSentStory = story;
    }

    @Override
    @Nullable
    public IncomingMessage getPreviousMessage() {
        return previousMessage;
    }

    @Override
    public void setPreviousMessage(@Nullable IncomingMessage previousMessage) {
        this.previousMessage = previousMessage;
    }

    @Override
    public String toString() {
        return "VkUserContext{" +
            "user=" + user +
            ", lastSentStory=" + lastSentStory +
            ", previousMessage=" + previousMessage +
            '}';
    }

}