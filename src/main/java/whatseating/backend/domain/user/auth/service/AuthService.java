package whatseating.backend.domain.user.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whatseating.backend.domain.user.auth.exception.InvalidTokenException;
import whatseating.backend.domain.user.auth.presentation.dto.request.CreateUserRequestDto;
import whatseating.backend.domain.user.auth.presentation.dto.request.LoginRequestDto;
import whatseating.backend.domain.user.auth.presentation.dto.response.TokenResponseDto;
import whatseating.backend.domain.user.user.domain.User;
import whatseating.backend.domain.user.user.domain.repository.UserRepository;
import whatseating.backend.domain.user.user.exception.DuplicatedUserEmailException;
import whatseating.backend.domain.user.user.exception.DuplicatedUserNameException;
import whatseating.backend.domain.user.user.exception.PasswordMismatchException;
import whatseating.backend.domain.user.user.exception.UserNotFoundException;
import whatseating.backend.global.config.auth.PasswordEncoder;
import whatseating.backend.global.security.jwt.JwtTokenProvider;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(CreateUserRequestDto dto) {
        // 이메일 중복 확인
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw DuplicatedUserEmailException.EXCEPTION;
        }
        // 이름 중복 확인
        if (userRepository.findByName(dto.getName()).isPresent()) {
            throw DuplicatedUserNameException.EXCEPTION;
        }

        userRepository.save(dto.toEntity(passwordEncoder.encodePassword().encode(dto.getPassword())));
    }

    @Transactional
    public TokenResponseDto login(LoginRequestDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        // 비밀번호 일치 여부 확인
        if (!passwordEncoder.encodePassword().matches(dto.getPassword(), user.getPassword())) {
            throw PasswordMismatchException.EXCEPTION;
        }

        String accessToken = jwtTokenProvider.createAccessToken(user);
        String refreshToken = jwtTokenProvider.createRefreshToken(user);

        return TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    // TODO: 로그아웃

    @Transactional
    public TokenResponseDto reissueAccessToken(String refreshToken) {
        // 리프레시 토큰 유효성 검사
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw InvalidTokenException.EXCEPTION;
        }

        User user = userRepository.findByEmail(jwtTokenProvider.getEmail(refreshToken))
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        String newAccessToken = jwtTokenProvider.createAccessToken(user);
        String newRefreshToken = jwtTokenProvider.createRefreshToken(user);

        return TokenResponseDto.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }
}
