package ewp.tasktracker.repository;

import ewp.tasktracker.entity.SprintEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SprintRepository extends JpaRepository<SprintEntity, String> {
}
