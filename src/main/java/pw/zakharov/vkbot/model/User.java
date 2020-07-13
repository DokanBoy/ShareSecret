package pw.zakharov.vkbot.model;

import com.google.common.collect.Lists;

import javax.persistence.*;
import java.util.List;

/**
 * @author Alexey Zakharov
 * @since 03.06.2020
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final int id;

    @Column(name = "name")
    private final String name;

    @OneToMany(mappedBy = "secrets", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Story> stories;

    public User(int id, String name, List<Story> stories) {
        this.id = id;
        this.name = name;
        this.stories = stories;
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.stories = Lists.newArrayList();
    }

    public static User of(int id) {
        return new User(id, null, null);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Story> getStories() {
        return stories;
    }

}
