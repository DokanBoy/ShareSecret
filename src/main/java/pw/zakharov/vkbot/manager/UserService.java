package pw.zakharov.vkbot.manager;

import org.jetbrains.annotations.NotNull;
import pw.zakharov.vkbot.model.Story;
import pw.zakharov.vkbot.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Created by: Alexey Zakharov <alexey@zakharov.pw>
 * Date: 14.07.2020 2:23
 */

public interface UserService {

    /**
     * @param name    user name (name + last name maybe)
     * @param vkId    user vk id
     * @param stories user stories, if user does`not have stories can be passed on List#of()
     * @return the user that you created
     */
    @NotNull User createUser(@NotNull String name, @NotNull Integer vkId, @NotNull List<Story> stories);

    /**
     * @param id User id
     * @return User by id
     */
    Optional<User> getUser(@NotNull Long id);

    /**
     * @param id User by id to be updated
     */
    void updateUser(@NotNull User id);

    /**
     * @param vkId User id
     * @return User by vk id
     */
    Optional<User> findUserByVkId(@NotNull Integer vkId);

    /**
     * @param vkId Vk user Id
     * @return true if the user with this id exists in the repository
     */
    boolean containsUserByVkId(@NotNull Integer vkId);

    void addStory(@NotNull Long userId, Story story);

    void removeStory(@NotNull Long userId, Long storyId);

}
