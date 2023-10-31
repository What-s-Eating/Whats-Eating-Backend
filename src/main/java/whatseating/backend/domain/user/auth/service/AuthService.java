package whatseating.backend.domain.user.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whatseating.backend.domain.user.auth.exception.InvalidTokenException;
import whatseating.backend.domain.user.auth.presentation.dto.request.CreateUserRequestDto;
import whatseating.backend.domain.user.auth.presentation.dto.request.LoginRequestDto;
import whatseating.backend.domain.user.auth.presentation.dto.response.TokenResponseDto;
import whatseating.backend.domain.user.user.domain.User;
import whatseating.backend.domain.user.user.domain.repository.UserRepository;
import whatseating.backend.domain.user.user.exception.DuplicatedUserEmailException;
import whatseating.backend.domain.user.user.exception.PasswordMismatchException;
import whatseating.backend.domain.user.user.exception.UserNotFoundException;
import whatseating.backend.global.security.dto.OAuthAttributes;
import whatseating.backend.global.security.jwt.JwtTokenProvider;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(CreateUserRequestDto dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw DuplicatedUserEmailException.EXCEPTION;
        }
        if (userRepository.findByName(dto.getName()).isPresent()) {
            throw DuplicatedUserEmailException.EXCEPTION;
        }

        userRepository.save(dto.toEntity(passwordEncoder.encode(dto.getPassword())));
    }

    @Transactional
    public TokenResponseDto login(LoginRequestDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw PasswordMismatchException.EXCEPTION;
        }

        String accessToken = jwtTokenProvider.createAccessToken(user);
        String refreshToken = jwtTokenProvider.createRefreshToken(user);

        return TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    // TODO: 구글 콜백 로직


    // TODO: 카카오 콜백 로직
    public void kakaoCallback(String code) {

    }

    // TODO: 네이버 콜백 로직


    // TODO: 로그아웃

    @Transactional
    public TokenResponseDto reissueAccessToken(String refreshToken) {
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw InvalidTokenException.EXCEPTION;
        }

        User user = userRepository.findByEmail(jwtTokenProvider.getEmail(refreshToken))
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        return TokenResponseDto.builder()
                .accessToken(jwtTokenProvider.createAccessToken(user))
                .build();
    }
}
