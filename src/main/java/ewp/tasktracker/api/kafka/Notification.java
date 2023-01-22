package ewp.tasktracker.api.kafka;

import lombok.Data;
import lombok.Value;

@Data
@Value
public class Notification {
    private String text;
    private String userId;
}
