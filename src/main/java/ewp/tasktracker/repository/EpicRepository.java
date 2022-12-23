package ewp.tasktracker.repository;

import ewp.tasktracker.entity.EpicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpicRepository extends JpaRepository<EpicEntity, String> {}
