package whatseating.backend.domain.place.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import whatseating.backend.domain.place.domain.Place;
import whatseating.backend.domain.place.exception.PlaceNotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Builder
public class PlaceResponseDto {
    private String id;
    private String place_name;
    private String category_name;
    private String category_group_code;
    private String category_group_name;
    private String address_name;
    private String road_address_name;
    private String x;
    private String y;
    private String place_url;
    private String distance;
    private String homepage_url;
    private String img_url;
    private String menu;
    private String phone;
    private String star;
    private String time;

    public static PlaceResponseDto of(Place place) {
        if (place == null) throw PlaceNotFoundException.EXCEPTION;

        return PlaceResponseDto.builder()
                .id(place.getId())
                .place_name(place.getPlace_name())
                .category_name(place.getCategory_name())
                .category_group_code(place.getCategory_group_code())
                .category_group_name(place.getCategory_group_name())
                .phone(place.getPhone())
                .address_name(place.getAddress_name())
                .road_address_name(place.getRoad_address_name())
                .x(place.getX())
                .y(place.getY())
                .place_url(place.getPlace_url())
                .distance(place.getDistance())
                .build();
    }

    public static List<PlaceResponseDto> of(List<Place> restaurants) {
        if (restaurants == null) throw PlaceNotFoundException.EXCEPTION;

        return restaurants.stream()
                .map(PlaceResponseDto::of)
                .collect(Collectors.toList());
    }
}
