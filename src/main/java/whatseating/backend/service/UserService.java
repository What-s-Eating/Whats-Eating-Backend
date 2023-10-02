package whatseating.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import whatseating.backend.domain.User;
import whatseating.backend.dto.user.request.CreateUserRequestDto;
import whatseating.backend.dto.user.request.DeleteUserRequestDto;
import whatseating.backend.dto.user.request.UpdateNameRequestDto;
import whatseating.backend.dto.user.request.UpdatePasswordRequestDto;
import whatseating.backend.dto.user.response.UserResponseDto;
import whatseating.backend.exception.user.DuplicatedUserEmailException;
import whatseating.backend.exception.user.DuplicatedUserNameException;
import whatseating.backend.exception.user.PasswordMismatchException;
import whatseating.backend.repository.UserRepository;

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
