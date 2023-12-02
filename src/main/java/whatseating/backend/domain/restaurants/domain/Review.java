package whatseating.backend.domain.restaurants.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import whatseating.backend.domain.user.user.domain.User;
import whatseating.backend.global.entity.BaseTimeEntity;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "review")
public class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "review_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String star;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="restaurants_id")
    private Restaurants restaurants;

    @Builder
    public Review(UUID id, String content, String star, User user, Restaurants restaurants) {
        this.id = id;
        this.content = content;
        this.star = star;
        this.user = user;
        this.restaurants = restaurants;
    }
}
