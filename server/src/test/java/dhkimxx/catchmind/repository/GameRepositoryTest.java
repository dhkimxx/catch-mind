package dhkimxx.catchmind.repository;

import dhkimxx.catchmind.entity.Game;
import dhkimxx.catchmind.enums.GameStatus;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

@Slf4j
@DataJpaTest
public class GameRepositoryTest {

    @Autowired
    GameRepository gameRepository;

    @Test
    @DisplayName("Create Game")
    void create() {
        // given
        Game game = Game.builder()
                .title("test title")
                .currentUserCount(0)
                .limitUserCount(8)
                .status(GameStatus.READY.name())
                .build();

        // when
        Game createdGame = gameRepository.save(game);

        // then
        log.info(createdGame.toString());
        Assertions.assertThat(createdGame.getId()).isInstanceOf(UUID.class);
        Assertions.assertThat(createdGame.getTitle()).isEqualTo("test title");
        Assertions.assertThat(createdGame.getCurrentUserCount()).isEqualTo(0);
        Assertions.assertThat(createdGame.getLimitUserCount()).isEqualTo(8);
        Assertions.assertThat(createdGame.getStatus()).isEqualTo(GameStatus.READY.name());
    }

    @Test
    void read() {

    }

    @Test
    void update() {

    }

    @Test
    void delete() {

    }
}