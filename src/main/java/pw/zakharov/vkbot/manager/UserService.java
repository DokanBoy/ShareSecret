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
     * @param name user name (name + last name maybe)
     * @param vkId  user vk id
     * @param stories user stories, if user does`not have stories can be passed on List#of()
     * @return user
     */
    User createUser(@NotNull String name, @NotNull Integer vkId, @NotNull List<Story> stories);

    /**
     * @param id User id
     * @return User by id
     */
    Optional<User> getUser(@NotNull Long id);

    /**
     * @param vkId User id
     * @return User by vk id
     */
    Optional<User> getUserByVkId(@NotNull Integer vkId);

    /**
     * @param user User to be updated
     */
    void updateUser(@NotNull User user);

    boolean containsUserByVkId(Integer vkId);

}
