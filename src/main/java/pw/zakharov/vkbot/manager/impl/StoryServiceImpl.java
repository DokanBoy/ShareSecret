package pw.zakharov.vkbot.manager.impl;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import pw.zakharov.vkbot.manager.StoryService;
import pw.zakharov.vkbot.model.Story;
import pw.zakharov.vkbot.model.User;
import pw.zakharov.vkbot.repository.StoryRepository;

import java.util.Optional;

/**
 * Created by: Alexey Zakharov <alexey@zakharov.pw>
 * Date: 14.07.2020 2:25
 */

@Service
public class StoryServiceImpl implements StoryService {

    private final StoryRepository storyRepository;

    public StoryServiceImpl(@NotNull StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    @Override
    public Long createStory(@NotNull User owner, @NotNull String text) {
        return storyRepository.save(new Story(null, owner, text, 0, 0)).getId();
    }

    @Override
    public Story getStory(@NotNull Long id) {
        Optional<Story> story = storyRepository.findById(id);
        return story.orElseThrow(() -> new NullPointerException("Can't find story with this id"));
    }

}
