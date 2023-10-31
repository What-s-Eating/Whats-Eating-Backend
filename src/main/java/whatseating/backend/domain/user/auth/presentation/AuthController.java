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

    // TODO: 구글 콜백 로직


    // TODO: 카카오 콜백 로직
    @GetMapping("/kakao")
    public void kakaoCallback(@RequestParam String code) {
        System.out.println(code);
    }

    // TODO: 네이버 콜백 로직


    // TODO: 로그아웃

    @PutMapping("/refresh")
    public TokenResponseDto reissueAccessToken(@RequestHeader(value = "Refresh-Token") String refreshToken) {
        return authService.reissueAccessToken(refreshToken);
    }
}
