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
//    private final KakaoService kakaoService;

    @PostMapping("/signup")
    public void signUp(@RequestBody @Valid CreateUserRequestDto dto) {
        authService.signUp(dto);
    }

    @PostMapping("/login")
    public TokenResponseDto login(@RequestBody @Valid LoginRequestDto dto) {
        return authService.login(dto);
    }

//    // TODO: 구글 콜백 로직
//    @GetMapping("/google/callback")
//    public void googleLogin(@RequestParam String code) {
//        authService.googleLogin(code);
//    }
//
    // TODO: 카카오 콜백 로직
//    @GetMapping("/kakao/callback")
//    @ResponseBody
//    public void kakaoLogin(@RequestParam String code) {
//        return kakaoService.kakaoLogin(code);
//    }
//
//
//    // TODO: 네이버 콜백 로직
//    @GetMapping("/naver/callback")
//    public void naverLogin(@RequestParam String code) {
//        authService.naverLogin(code);
//    }


    // TODO: 로그아웃

    @PutMapping("/refresh")
    public TokenResponseDto reissueAccessToken(@RequestHeader(value = "Refresh-Token") String refreshToken) {
        return authService.reissueAccessToken(refreshToken);
    }
}
