package whatseating.backend.domain.restaurants.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import whatseating.backend.domain.restaurants.domain.Restaurants;
import whatseating.backend.domain.restaurants.exception.RestaurantsNotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Builder
public class RestaurantsResponseDto {
    private UUID id;
    private String latitude;
    private String longitude;
    private String mainCategory;
    private String menu;
    private String phone;
    private String time;
    private String star;
    private String placeName;
    private String address;
    private String homepageUrl;
    private String imgUrl;

    public static RestaurantsResponseDto of(Restaurants restaurants) {
        if (restaurants == null) throw RestaurantsNotFoundException.EXCEPTION;

        return RestaurantsResponseDto.builder()
                .id(restaurants.getId())
                .latitude(restaurants.getLatitude())
                .longitude(restaurants.getLongitude())
                .mainCategory(restaurants.getMainCategory())
                .menu(restaurants.getMenu())
                .phone(restaurants.getPhone())
                .time(restaurants.getTime())
                .star(restaurants.getStar())
                .placeName(restaurants.getPlaceName())
                .address(restaurants.getAddress())
                .homepageUrl(restaurants.getHomepageUrl())
                .imgUrl(restaurants.getImgUrl())
                .build();
    }

    public static List<RestaurantsResponseDto> of(List<Restaurants> restaurants) {
        if (restaurants == null) throw RestaurantsNotFoundException.EXCEPTION;

        return restaurants.stream()
                .map(RestaurantsResponseDto::of)
                .collect(Collectors.toList());
    }
}
