package whatseating.backend.domain.user.auth.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import whatseating.backend.domain.user.auth.presentation.dto.request.CreateUserRequestDto;
import whatseating.backend.domain.user.auth.presentation.dto.request.LoginRequestDto;
import whatseating.backend.domain.user.auth.presentation.dto.response.TokenResponseDto;
import whatseating.backend.domain.user.auth.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public void signUp(@RequestBody @Valid CreateUserRequestDto dto) {
        authService.signUp(dto);
    }

    @PostMapping("/login")
    public TokenResponseDto login(@RequestBody @Valid LoginRequestDto dto) {
        return authService.login(dto);
    }

    // TODO: 로그아웃

    @PutMapping("/refresh")
    public TokenResponseDto reissueAccessToken(@RequestHeader("Authorization") String refreshToken) {
        return authService.reissueAccessToken(refreshToken);
    }
}
