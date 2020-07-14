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

public interface UserContext {

    @NotNull
    User getContextOwner();

    @Nullable
    Story getLastSentStory();

    void setLastSentStory(@Nullable Story story);

    @Nullable
    IncomingMessage getPreviousMessage();

    void setPreviousMessage(@Nullable IncomingMessage previousMessage);

}
