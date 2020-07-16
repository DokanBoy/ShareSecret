package pw.zakharov.vkbot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by: Alexey Zakharov <alexey@zakharov.pw>
 * Date: 14.07.2020 1:34
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "vk_id")
    private Integer vkId;

    @OneToMany(mappedBy = "stories", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Story> stories;

}
