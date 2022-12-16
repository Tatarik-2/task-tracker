package ewp.tasktracker.repository;

import ewp.tasktracker.entity.DeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с поставками
 */
@Repository
public interface DeliveryRepository extends JpaRepository<DeliveryEntity, String> {}