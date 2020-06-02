package pw.zakharow.vkbot.persistance;

import com.google.common.collect.Lists;

import javax.persistence.*;
import java.util.List;

/**
 * @author Alexey Zakharov
 * @date 03.06.2020
 */

@Entity
@Table(name = "users")
public final class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final int id;

    @Column(name = "name")
    private final String name;

    @OneToMany(mappedBy = "secrets", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Secret> secrets;

    public User(int id, String name, List<Secret> secrets) {
        this.id = id;
        this.name = name;
        this.secrets = secrets;
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.secrets = Lists.newArrayList();
    }

    public static User of(int id) {
        return new User(id, null, List.of());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Secret> getSecrets() {
        return secrets;
    }

}
