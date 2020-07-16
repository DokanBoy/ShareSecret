package pw.zakharov.vkbot.manager.impl;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import pw.zakharov.vkbot.manager.UserService;
import pw.zakharov.vkbot.model.Story;
import pw.zakharov.vkbot.model.User;
import pw.zakharov.vkbot.repository.UserRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by: Alexey Zakharov <alexey@zakharov.pw>
 * Date: 14.07.2020 2:25
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public @NotNull User createUser(@NotNull String name, @NotNull Integer vkId, @NotNull List<Story> stories) {
        User user = new User(null, name, vkId, stories);
        userRepository.save(user);

        return user;
    }

    @Override
    public @NotNull User getOrCreate(@NotNull String name,
                                     @NotNull Integer vkId,
                                     @NotNull List<Story> stories) {
        if (findUserByVkId(vkId).isEmpty())
            return createUser(name, vkId, stories);

        return findUserByVkId(vkId).get();
    }

    @Override
    public Optional<User> getUser(@NotNull Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findUserByVkId(@NotNull Integer vkId) {
        return Optional.empty();
    }

    @Override
    public void updateUser(@NotNull User user) {
        userRepository.deleteById(user.getId());
        userRepository.save(user);
    }

    @Override
    public boolean containsUserByVkId(@NotNull Integer vkId) {
        // TODO
        return false;
    }

    @Override
    public void addStory(@NotNull Long userId, @NotNull Story story) {
        User user = getUser(userId).orElseThrow();
        user.getStories().add(story);

        updateUser(user);
    }

    @Override
    public void removeStory(@NotNull Long userId, @NotNull Long storyId) {
        User user = getUser(userId).orElseThrow();
        user.getStories().removeIf(story -> story.getId().equals(storyId));

        updateUser(user);
    }

}
