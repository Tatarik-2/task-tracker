package ewp.tasktracker.repository;

import ewp.tasktracker.entity.WorkloadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с поставками
 */
@Repository
public interface WorkloadRepository extends JpaRepository<WorkloadEntity, String> {}