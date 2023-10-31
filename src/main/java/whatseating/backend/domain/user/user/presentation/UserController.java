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

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @DeleteMapping
    public void deleteUser(@RequestBody @Valid DeleteUserRequestDto dto, @RequestAttribute User user) {
        userService.deleteUser(dto, user);
    }

    // TODO: 유저 정보 가져오기
    @GetMapping("/me")
    public UserResponseDto getUserInfo(@RequestAttribute User user) {
        return userService.getUserInfo(user);
    }

    @PutMapping("/name")
    public void updateUserName(@RequestBody @Valid UpdateNameRequestDto dto, @RequestAttribute User user) {
        userService.updateUserName(dto, user);
    }

    @PutMapping("/password")
    public void updateUserPassword(@RequestBody @Valid UpdatePasswordRequestDto dto, @RequestAttribute User user) {
        userService.updateUserPassword(dto, user);
    }

    @PutMapping("/profile-img")
    public void updateUserProfileImg(@RequestParam String profileImage, @RequestAttribute User user) {
        userService.updateUserProfileImage(profileImage, user);
    }
}
