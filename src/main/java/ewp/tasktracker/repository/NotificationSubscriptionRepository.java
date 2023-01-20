package ewp.tasktracker.repository;

import ewp.tasktracker.entity.NotificationSubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationSubscriptionRepository extends JpaRepository<NotificationSubscriptionEntity, String> {
}
