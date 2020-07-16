package pw.zakharov.vkbot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pw.zakharov.vkbot.model.User;

/**
 * Created by: Alexey Zakharov <alexey@zakharov.pw>
 * Date: 14.07.2020 1:34
 */
public interface UserRepository extends CrudRepository<User, Long> {

}