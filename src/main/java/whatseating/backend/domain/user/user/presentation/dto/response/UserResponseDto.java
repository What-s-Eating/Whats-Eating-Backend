package whatseating.backend.domain.user.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import whatseating.backend.domain.user.user.domain.User;
import whatseating.backend.domain.user.user.domain.enums.Provider;
import whatseating.backend.domain.user.user.domain.enums.Role;
import whatseating.backend.domain.user.user.exception.UserNotFoundException;

@Getter
@Builder
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private String profileImage;
    private Provider provider;
    private Role role;

    public static UserResponseDto of(User user) {
        if (user == null) throw UserNotFoundException.EXCEPTION;

        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .profileImage(user.getProfileImage())
                .provider(user.getProvider())
                .role(user.getRole())
                .build();
    }
}
