package whatseating.backend.domain.restaurants.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import whatseating.backend.domain.restaurants.presentation.dto.request.ReviewRequestDto;
import whatseating.backend.domain.restaurants.presentation.dto.response.RestaurantsResponseDto;
import whatseating.backend.domain.restaurants.presentation.dto.response.ReviewResponseDto;
import whatseating.backend.domain.restaurants.service.RestaurantsService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantsController {
    private final RestaurantsService restaurantsService;

    @GetMapping
    public List<RestaurantsResponseDto> getNearbyRestaurants(@RequestParam String latitude, @RequestParam String longitude) {
        return restaurantsService.getNearbyRestaurants(latitude, longitude);
    }

    @GetMapping("/{id}")
    public RestaurantsResponseDto getRestaurantInfo(@PathVariable UUID id) {
        return restaurantsService.getRestaurantInfo(id);
    }

    @GetMapping("/{id}/reviews")
    public List<ReviewResponseDto> getReviews(@PathVariable UUID id, @RequestParam(defaultValue = "1") int page) {
        return restaurantsService.getReviews(id, page);
    }

    @PostMapping("/{id}/reviews")
    public void addReviews(@RequestBody @Valid ReviewRequestDto dto, @PathVariable UUID id) {
        restaurantsService.addReviews(dto, id);
    }

    @PutMapping("/{id}/reviews/{review_id}")
    public void updateReviews(@RequestBody @Valid ReviewRequestDto dto, @PathVariable UUID id, @PathVariable UUID review_id) {
        restaurantsService.updateReviews(dto, id, review_id);
    }

    @DeleteMapping("/{id}/reviews/{review_id}")
    public void deleteReviews(@PathVariable UUID id, @PathVariable UUID review_id) {
        restaurantsService.deleteReviews(id, review_id);
    }
}
