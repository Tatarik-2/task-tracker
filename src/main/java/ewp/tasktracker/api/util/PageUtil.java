package ewp.tasktracker.api.util;


import ewp.tasktracker.config.TaskTrackerProperties;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PageUtil {

    private final TaskTrackerProperties props;

    public Integer pageSizeCheck(Integer pageSizeRq) {
        if (pageSizeRq == null) {
            pageSizeRq = props.getPageDefaultSize();
        }
        if (pageSizeRq > props.getPageMaxSize()) {
            pageSizeRq = props.getPageMaxSize();
        }
        return pageSizeRq;
    }
}
