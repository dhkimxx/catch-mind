package dhkimxx.catchmind.dto;

import dhkimxx.catchmind.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;


@Builder
@Getter
public class UserResponseDto {
    private UUID id;
    private String email;
    private String nickname;

    public static UserResponseDto fromEntity(User user){
        return UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .build();
    }
}
