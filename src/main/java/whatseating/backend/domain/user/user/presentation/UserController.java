package whatseating.backend.domain.user.user.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import whatseating.backend.domain.user.user.domain.User;
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

    @DeleteMapping
    public void deleteUser(@RequestBody @Valid DeleteUserRequestDto dto, User user) {
        userService.deleteUser(dto, user);
    }

    @GetMapping("/me")
    public UserResponseDto getUserInfo(@UserId Long id) {
        return userService.getUserInfo(id);
    }

    @PutMapping("/name")
    public void updateUserName(@RequestBody @Valid UpdateNameRequestDto dto, User user) {
        userService.updateUserName(dto, user);
    }

    @PutMapping("/password")
    public void updateUserPassword(@RequestBody @Valid UpdatePasswordRequestDto dto, User user) {
        userService.updateUserPassword(dto, user);
    }

    @PutMapping("/profile-img")
    public void updateUserProfileImg(@RequestParam String profileImage, User user) {
        userService.updateUserProfileImage(profileImage, user);
    }
}
