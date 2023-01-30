package ewp.tasktracker.api.sheduler;

import org.springframework.stereotype.Service;

public interface SchedulerService {
    SchedulerInfo save(SchedulerInfo schedulerInfo);

    SchedulerInfo findById(String id);
}
