package whatseating.backend.domain.place.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import whatseating.backend.domain.place.presentation.dto.request.ReviewRequestDto;
import whatseating.backend.domain.place.presentation.dto.response.PlaceResponseDto;
import whatseating.backend.domain.place.presentation.dto.response.ReviewResponseDto;
import whatseating.backend.domain.place.service.PlaceService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/place")
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;

    @GetMapping
    public List<PlaceResponseDto> getNearbyPlace(@RequestParam String x, @RequestParam String y) {
        return placeService.getNearbyPlace(x, y);
    }

    @GetMapping("/{id}")
    public PlaceResponseDto getPlaceInfo(@PathVariable UUID id) {
        return placeService.getPlaceInfo(id);
    }

    @GetMapping("/{id}/reviews")
    public List<ReviewResponseDto> getReviews(@PathVariable UUID id, @RequestParam(defaultValue = "1") int page) {
        return placeService.getReviews(id, page);
    }

    @PostMapping("/{id}/reviews")
    public void addReviews(@RequestBody @Valid ReviewRequestDto dto, @PathVariable UUID id) {
        placeService.addReviews(dto, id);
    }

    @PutMapping("/{id}/reviews/{review_id}")
    public void updateReviews(@RequestBody @Valid ReviewRequestDto dto, @PathVariable UUID id, @PathVariable UUID review_id) {
        placeService.updateReviews(dto, id, review_id);
    }

    @DeleteMapping("/{id}/reviews/{review_id}")
    public void deleteReviews(@PathVariable UUID id, @PathVariable UUID review_id) {
        placeService.deleteReviews(id, review_id);
    }
}
