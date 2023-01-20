package ewp.tasktracker.service.notificationSubscription;

import ewp.tasktracker.api.dto.notificationSubscription.NotificationSubscriptionDto;

import java.util.List;

public interface NotificationSubscriptionService {
    List<NotificationSubscriptionDto> findAll(Integer pageSize, Integer pageNumber);
}
