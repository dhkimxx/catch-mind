package dhkimxx.catchmind.repository;

import dhkimxx.catchmind.entity.UserGameInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserGameInfoRepository extends JpaRepository<UserGameInfo, UUID> {
}
