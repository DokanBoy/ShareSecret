package pw.zakharov.vkbot.persistance;

import javax.persistence.*;

/**
 * @author Alexey Zakharov
 * @date 31.05.2020
 */

@Entity
@Table(name = "secrets")
public final class Secret {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "text")
    private final String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private final User owner;

    @Column(name = "likes")
    private int likes;

    @Column(name = "dislikes")
    private int dislikes;

    public Secret(User owner, String text) {
        this.owner = owner;
        this.text = text;

        this.likes = 0;
        this.dislikes = 0;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public User getOwner() {
        return owner;
    }

    public void inc() {
        ++likes;
    }

    public void dec() {
        ++dislikes;
    }

}
