package whatseating.backend.domain.restaurants.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import whatseating.backend.domain.user.user.domain.User;
import whatseating.backend.global.entity.BaseTimeEntity;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "comments")
public class Comments extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="id")
    private Restaurants restaurants;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String star;

    @Builder
    public Comments(User user, Restaurants restaurants, String content, String star) {
        this.user = user;
        this.restaurants = restaurants;
        this.content = content;
        this.star = star;
    }
}
