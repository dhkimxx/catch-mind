package dhkimxx.catchmind.dto;

import dhkimxx.catchmind.entity.Game;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class GameResponseDto {
    private UUID id;
    private String title;
    private Integer limitUserCount;
    private Integer currentUserCount;
    private String status;

    public static GameResponseDto fromEntity(Game game) {
        return GameResponseDto.builder()
                .id(game.getId())
                .title(game.getTitle())
                .limitUserCount(game.getLimitUserCount())
                .currentUserCount(game.getCurrentUserCount())
                .status(game.getStatus())
                .build();
    }
}
