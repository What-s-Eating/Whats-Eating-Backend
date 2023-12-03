package whatseating.backend.domain.place.domain;

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
@Table(name = "place")
public class Place {
    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "place_id", columnDefinition = "BINARY(16)")
    private String id;

    @Column(nullable = false)
    private String place_name;

    @Column(nullable = false)
    private String category_name;

    @Column(nullable = false)
    private String category_group_code;

    @Column(nullable = false)
    private String category_group_name;

    @Column(nullable = false)
    private String address_name;

    @Column(nullable = false)
    private String road_address_name;

    @Column(nullable = false)
    private String x;

    @Column(nullable = false)
    private String y;

    @Column(nullable = false)
    private String place_url;

    @Column(nullable = false)
    private String distance;

    private String homepage_url;

    private String img_url;

    private String menu;

    @Column(nullable = false)
    private String phone;

    private String star;

    private String time;

    @OneToMany(mappedBy = "place")
    private List<Review> review = new ArrayList<Review>();

    @Builder
    public Place(String id, String place_name, String category_name, String category_group_code, String category_group_name, String address_name, String road_address_name, String x, String y, String place_url, String distance, String homepage_url, String img_url, String menu, String phone, String star, String time, List<Review> review) {
        this.id = id;
        this.place_name = place_name;
        this.category_name = category_name;
        this.category_group_code = category_group_code;
        this.category_group_name = category_group_name;
        this.address_name = address_name;
        this.road_address_name = road_address_name;
        this.x = x;
        this.y = y;
        this.place_url = place_url;
        this.distance = distance;
        this.homepage_url = homepage_url;
        this.img_url = img_url;
        this.menu = menu;
        this.phone = phone;
        this.star = star;
        this.time = time;
        this.review = review;
    }
}
