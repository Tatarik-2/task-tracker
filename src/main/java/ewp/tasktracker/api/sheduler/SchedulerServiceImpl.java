package ewp.tasktracker.api.sheduler;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SchedulerServiceImpl implements SchedulerService {
    private final SchedulerRepository repository;

    @Override
    public SchedulerInfo save(SchedulerInfo schedulerInfo) {
        SchedulerInfo schedulerInfo1 = repository.save(schedulerInfo);
        return schedulerInfo1;
    }

    @Override
    public SchedulerInfo findById(String id) {
        return repository.findById(id).orElse(null);
    }
}
