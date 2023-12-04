package whatseating.backend.domain.user.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whatseating.backend.domain.user.user.domain.User;
import whatseating.backend.domain.user.user.domain.repository.UserRepository;
import whatseating.backend.domain.user.user.exception.DuplicatedUserNameException;
import whatseating.backend.domain.user.user.exception.PasswordMismatchException;
import whatseating.backend.domain.user.user.exception.UserNotFoundException;
import whatseating.backend.domain.user.user.presentation.dto.request.DeleteUserRequestDto;
import whatseating.backend.domain.user.user.presentation.dto.request.UpdateNameRequestDto;
import whatseating.backend.domain.user.user.presentation.dto.request.UpdatePasswordRequestDto;
import whatseating.backend.domain.user.user.presentation.dto.response.UserResponseDto;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public UserResponseDto getUserInfo(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        return UserResponseDto.of(user);
    }

    @Transactional
    public void updateUserName(UpdateNameRequestDto dto, String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        // 이름 중복 확인
        if (userRepository.findByName(dto.getName()).isPresent()) {
            throw DuplicatedUserNameException.EXCEPTION;
        }

        user.updateName(dto.getName());
        userRepository.save(user);
    }

    @Transactional
    public void updateUserPassword(UpdatePasswordRequestDto dto, String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        // 비밀번호 일치 여부 확인
        if (!passwordEncoder.matches(dto.getCurrentPassword(), user.getPassword())) {
            throw PasswordMismatchException.EXCEPTION;
        }

        user.updatePassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public void updateUserProfileImage(String profileImage, String id) {
        // TODO: 나중에 S3로 변경
        User user = userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        user.updateProfileImage(profileImage);
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(DeleteUserRequestDto dto, String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        // 비밀번호 일치 여부 확인
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw PasswordMismatchException.EXCEPTION;
        }

        userRepository.delete(user);
    }
}
