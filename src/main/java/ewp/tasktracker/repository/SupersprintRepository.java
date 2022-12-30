package ewp.tasktracker.repository;

import ewp.tasktracker.entity.SupersprintEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с суперспринтами
 */
@Repository
public interface SupersprintRepository extends JpaRepository<SupersprintEntity, String> {}