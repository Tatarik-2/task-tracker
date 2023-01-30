package ewp.tasktracker.api.sheduler;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulerRepository extends JpaRepository<SchedulerInfo, String> {
}
