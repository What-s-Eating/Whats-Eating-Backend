package whatseating.backend.domain.place.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import whatseating.backend.domain.user.user.domain.User;
import whatseating.backend.global.entity.BaseTimeEntity;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "review")
public class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String star;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="place_id", referencedColumnName = "id")
    private Place place;

    @Builder
    public Review(String id, String content, String star, User user, Place place) {
        this.id = id;
        this.content = content;
        this.star = star;
        this.user = user;
        this.place = place;
    }

    public void updateReviews(String content, String star) {
        this.content = content;
        this.star = star;
    }
}
