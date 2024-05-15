package dhkimxx.catchmind.service;

import dhkimxx.catchmind.dto.UserRequestDto;
import dhkimxx.catchmind.dto.UserResponseDto;
import dhkimxx.catchmind.entity.User;
import dhkimxx.catchmind.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDto create(UserRequestDto request) {
        User newUser = User.builder()
                .id(null)
                .email(request.getEmail())
                .password(request.getPassword())
                .randomKey(request.getRandomKey())
                .build();

        return UserResponseDto.fromEntity(userRepository.save(newUser));
    }

    public UserResponseDto update(UUID id, UserRequestDto request) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) throw new IllegalArgumentException("User not found with id: " + id);

        User targetUser = optionalUser.get();
//        if (request.getEmail() != null) targetUser.setEmail(request.getEmail());
        if (request.getNickname() != null) targetUser.setNickname(request.getNickname());
//        if (request.getPassword() != null && request.getRandomKey() != null) {
//            targetUser.setPassword(request.getPassword());
//            targetUser.setRandomKey(request.getRandomKey());
//        }
        return UserResponseDto.fromEntity(userRepository.save(targetUser));
    }

    public UserResponseDto read(UUID id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) throw new IllegalArgumentException("User not found with id: " + id);

        return UserResponseDto.fromEntity(optionalUser.get());
    }

    public void delete(UUID id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) throw new IllegalArgumentException("User not found with id: " + id);
        else userRepository.delete((optionalUser.get()));
    }
}
