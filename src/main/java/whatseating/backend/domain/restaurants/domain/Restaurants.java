package whatseating.backend.domain.restaurants.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "restaurants")
public class Restaurants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    // ex) 카페, 음식점
    @Column(nullable = false)
    private String mainCategory;

    private String menu;

    private String phone;

    private String time;

    private String star;

    private String comment;

    // ex) 홍대점
    @Column(nullable = false)
    private String placeName;

    @Column(nullable = false)
    private String address;

    private String homepageUrl;
    private String imgUrl;

    @OneToMany(mappedBy = "comments")
    private List<Comments> comments = new ArrayList<Comments>();

    @Builder
    public Restaurants(double latitude, double longitude, String mainCategory, String menu, String phone, String time, String star, String comment, String placeName, String address, String homepageUrl, String imgUrl) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.mainCategory = mainCategory;
        this.menu = menu;
        this.phone = phone;
        this.time = time;
        this.star = star;
        this.comment = comment;
        this.placeName = placeName;
        this.address = address;
        this.homepageUrl = homepageUrl;
        this.imgUrl = imgUrl;
    }
}
