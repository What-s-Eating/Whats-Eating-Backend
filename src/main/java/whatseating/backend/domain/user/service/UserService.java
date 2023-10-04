package whatseating.backend.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whatseating.backend.domain.user.domain.User;
import whatseating.backend.domain.user.presentation.dto.request.CreateUserRequestDto;
import whatseating.backend.domain.user.presentation.dto.request.DeleteUserRequestDto;
import whatseating.backend.domain.user.presentation.dto.request.UpdateNameRequestDto;
import whatseating.backend.domain.user.presentation.dto.request.UpdatePasswordRequestDto;
import whatseating.backend.domain.user.presentation.dto.response.UserResponseDto;
import whatseating.backend.domain.user.exception.DuplicatedUserEmailException;
import whatseating.backend.domain.user.exception.DuplicatedUserNameException;
import whatseating.backend.domain.user.exception.PasswordMismatchException;
import whatseating.backend.domain.user.domain.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createUser(CreateUserRequestDto dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw DuplicatedUserEmailException.EXCEPTION;
        }
        if (userRepository.findByName(dto.getName()).isPresent()) {
            throw DuplicatedUserNameException.EXCEPTION;
        }

        User user = dto.toEntity(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(DeleteUserRequestDto dto, User user) {
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw PasswordMismatchException.EXCEPTION;
        }

        userRepository.delete(user);
    }

    @Transactional(readOnly = true)
    public UserResponseDto getUserInfo(User user) {
        return UserResponseDto.of(user);
    }

    @Transactional
    public void updateUserName(UpdateNameRequestDto dto, User user) {
        if (userRepository.findByName(dto.getName()).isPresent()) {
            throw DuplicatedUserNameException.EXCEPTION;
        }

        user.updateName(dto.getName());
        userRepository.save(user);
    }

    @Transactional
    public void updateUserPassword(UpdatePasswordRequestDto dto, User user) {
        if (!passwordEncoder.matches(dto.getCurrentPassword(), user.getPassword())) {
            throw PasswordMismatchException.EXCEPTION;
        }

        user.updatePassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public void updateUserProfileImage(String profileImage, User user) {
        user.updateProfileImage(profileImage);
        userRepository.save(user);
    }
}
