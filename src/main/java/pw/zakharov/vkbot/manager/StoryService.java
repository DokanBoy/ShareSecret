package pw.zakharov.vkbot.manager;

import org.jetbrains.annotations.NotNull;
import pw.zakharov.vkbot.model.Story;
import pw.zakharov.vkbot.model.User;

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
    Long createStory(@NotNull User owner, @NotNull String text);

    /**
     * @param id Story id
     * @return Story by id
     */
    Story getStory(@NotNull Long id);

}
