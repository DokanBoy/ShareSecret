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
    public Long createUser(@NotNull String name, @NotNull List<Story> stories) {
        return userRepository.save(new User(null, name, stories)).getId();
    }

    @Override
    public User getUser(@NotNull Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new NullPointerException("Can't find user with this id"));
    }

    @Override
    public void updateUser(@NotNull User user) {
        userRepository.deleteById(user.getId());
        userRepository.save(user);
    }

}
