package pw.zakharov.vkbot.manager.impl;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import pw.zakharov.vkbot.manager.StoryService;
import pw.zakharov.vkbot.model.Like;
import pw.zakharov.vkbot.model.Story;
import pw.zakharov.vkbot.model.User;
import pw.zakharov.vkbot.repository.StoryRepository;

import java.util.List;
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
    public @NotNull Story createStory(@NotNull User owner, @NotNull String text) {
        Story story = new Story(null, owner, text, List.of());
        storyRepository.save(story);

        return story;
    }

    @Override
    public Optional<Story> getStory(@NotNull Long id) {
        return storyRepository.findById(id);
    }

    @Override
    public void updateStory(@NotNull Story story) {
        storyRepository.deleteById(story.getId());
        storyRepository.save(story);
    }

    @Override
    public @NotNull List<Like> like(@NotNull Story story, @NotNull Like like) {
        story.getLikes().add(like);
        storyRepository.save(story);

        return story.getLikes();
    }

}
