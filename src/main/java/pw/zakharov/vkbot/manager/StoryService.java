package pw.zakharov.vkbot.manager;

import org.jetbrains.annotations.NotNull;
import pw.zakharov.vkbot.model.Story;
import pw.zakharov.vkbot.model.User;

import java.util.Optional;

/**
 * Created by: Alexey Zakharov <alexey@zakharov.pw>
 * Date: 14.07.2020 2:24
 */

public interface StoryService {

    /**
     * @param owner Story owner
     * @param text  Story text
     * @return story id
     */
    Story createStory(@NotNull User owner, @NotNull String text);

    /**
     * @param id Story id
     * @return Story by id
     */
    Optional<Story> getStory(@NotNull Long id);

    /**
     * @param story Story to be updated
     */
    void updateStory(@NotNull Story story);
}
