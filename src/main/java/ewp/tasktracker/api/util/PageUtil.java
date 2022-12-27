package ewp.tasktracker.api.util;

import ewp.tasktracker.config.TaskTrackerProperties;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;

/**
 * Класс для для проверки условий при пагинации
 */

@AllArgsConstructor
@Component
public class PageUtil {
    private final TaskTrackerProperties props;

    public Integer pageSizeControl(Integer pageSize) {
        return pageSize == null ? props.getPageDefaultSize() : (Math.min(pageSize, props.getPageMaxSize()));
    }
}
