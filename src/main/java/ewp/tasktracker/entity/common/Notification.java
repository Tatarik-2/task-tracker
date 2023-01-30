package ewp.tasktracker.entity.common;

import lombok.Data;
import lombok.Value;

@Data
@Value
public class Notification {
    private String userId;
    private String text;
}
