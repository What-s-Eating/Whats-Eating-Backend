package whatseating.backend.domain.restaurants.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "restaurants")
public class Restaurants {
    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "restaurants_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private String latitude;

    @Column(nullable = false)
    private String longitude;

    // ex) 카페, 음식점
    @Column(nullable = false)
    private String mainCategory;

    private String menu;

    private String phone;

    private String time;

    private String star;

    // ex) 홍대점
    @Column(nullable = false)
    private String placeName;

    @Column(nullable = false)
    private String address;

    private String homepageUrl;
    private String imgUrl;

    @OneToMany(mappedBy = "restaurants")
    private List<Review> review = new ArrayList<Review>();

    @Builder
    public Restaurants(UUID id, String latitude, String longitude, String mainCategory, String menu, String phone, String time, String star, String placeName, String address, String homepageUrl, String imgUrl) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.mainCategory = mainCategory;
        this.menu = menu;
        this.phone = phone;
        this.time = time;
        this.star = star;
        this.placeName = placeName;
        this.address = address;
        this.homepageUrl = homepageUrl;
        this.imgUrl = imgUrl;
    }
}
