package ewp.tasktracker.api.dto.notificationSubscription;

import ewp.tasktracker.entity.NotificationSubscriptionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationSubscriptionDto {
    private String id;
    private String userId;
    private String projectId;

    public NotificationSubscriptionDto(NotificationSubscriptionEntity notificationSubscription) {
        this.id = notificationSubscription.getId();
        this.userId = notificationSubscription.getUserId();
        this.projectId = notificationSubscription.getProjectId();
    }
}
