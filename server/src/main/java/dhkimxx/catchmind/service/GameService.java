package dhkimxx.catchmind.service;

import dhkimxx.catchmind.dto.GameRequestDto;
import dhkimxx.catchmind.dto.GameResponseDto;
import dhkimxx.catchmind.entity.Game;
import dhkimxx.catchmind.enums.GameStatus;
import dhkimxx.catchmind.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    public GameResponseDto create(GameRequestDto request) {
        Game newGame = Game.builder()
                .id(null)
                .title(request.getTitle())
                .limitUserCount(request.getLimitUserCount())
                .currentUserCount(0)
                .status(GameStatus.READY.name())
                .build();

        return GameResponseDto.fromEntity(gameRepository.save(newGame));
    }

    public GameResponseDto update(UUID id, GameRequestDto request) {
        Optional<Game> optionalGame = gameRepository.findById(id);
        if (optionalGame.isEmpty()) throw new IllegalArgumentException("Game not found with id: " + id);

        Game targetGame = optionalGame.get();
        if(request.getTitle() != null) targetGame.setTitle(request.getTitle());
        if(request.getLimitUserCount() != null) targetGame.setLimitUserCount(request.getLimitUserCount());
        return GameResponseDto.fromEntity(gameRepository.save(targetGame));
    }

    public GameResponseDto read(UUID id) {
        Optional<Game> optionalGame = gameRepository.findById(id);
        if (optionalGame.isEmpty()) throw new IllegalArgumentException("Game not found with id: " + id);

        return GameResponseDto.fromEntity(optionalGame.get());
    }

    public void delete(UUID id) {
        Optional<Game> optionalGame = gameRepository.findById(id);
        if (optionalGame.isEmpty()) throw new IllegalArgumentException("Game not found with id: " + id);
        else gameRepository.delete((optionalGame.get()));
    }
}
