package whatseating.backend.domain.user.user.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import whatseating.backend.domain.user.user.presentation.dto.request.DeleteUserRequestDto;
import whatseating.backend.domain.user.user.presentation.dto.request.UpdateNameRequestDto;
import whatseating.backend.domain.user.user.presentation.dto.request.UpdatePasswordRequestDto;
import whatseating.backend.domain.user.user.presentation.dto.response.UserResponseDto;
import whatseating.backend.domain.user.user.service.UserService;
import whatseating.backend.global.config.resolver.UserId;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public UserResponseDto getUserInfo(@UserId String id) {
        return userService.getUserInfo(id);
    }

    @PutMapping("/name")
    public void updateUserName(@RequestBody @Valid UpdateNameRequestDto dto, @UserId String id) {
        userService.updateUserName(dto, id);
    }

    @PutMapping("/password")
    public void updateUserPassword(@RequestBody @Valid UpdatePasswordRequestDto dto, @UserId String id) {
        userService.updateUserPassword(dto, id);
    }

    @PutMapping("/profile-img")
    public void updateUserProfileImg(@RequestParam String profileImage, @UserId String id) {
        userService.updateUserProfileImage(profileImage, id);
    }

    @DeleteMapping
    public void deleteUser(@RequestBody @Valid DeleteUserRequestDto dto, @UserId String id) {
        userService.deleteUser(dto, id);
    }
}
