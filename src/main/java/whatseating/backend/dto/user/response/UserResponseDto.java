package whatseating.backend.dto.user.response;

import lombok.Builder;
import lombok.Getter;
import whatseating.backend.domain.User;
import whatseating.backend.domain.enums.Provider;
import whatseating.backend.domain.enums.Role;

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
