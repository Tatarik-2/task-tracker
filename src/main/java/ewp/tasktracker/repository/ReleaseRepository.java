package ewp.tasktracker.repository;

import ewp.tasktracker.entity.ReleaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с релизами
 */
@Repository
public interface ReleaseRepository extends JpaRepository<ReleaseEntity, String> {}