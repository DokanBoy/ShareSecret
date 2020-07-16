package pw.zakharov.vkbot.repository;

import org.springframework.data.repository.CrudRepository;
import pw.zakharov.vkbot.model.Story;

/**
 * Created by: Alexey Zakharov <alexey@zakharov.pw>
 * Date: 14.07.2020 1:34
 */
public interface StoryRepository extends CrudRepository<Story, Long> {

}
