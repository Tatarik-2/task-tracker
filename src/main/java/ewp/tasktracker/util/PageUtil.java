package ewp.tasktracker.util;

import ewp.tasktracker.config.TaskTrackerProperties;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class PageUtil {

    private final TaskTrackerProperties props;

    public Integer pageSizeCheck(Integer pageSize) {
        if (pageSize == null) {
            return props.getPageDefaultSize();
        } else if (pageSize == props.getPageMaxSize()) {
            return props.getPageMaxSize();
        } else {
            return pageSize;
        }
    }
}
