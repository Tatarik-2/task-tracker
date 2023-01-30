package ewp.tasktracker.api.sheduler;

import ewp.tasktracker.api.dto.bug.BugDto;
import ewp.tasktracker.api.dto.notificationSubscription.NotificationSubscriptionDto;
import ewp.tasktracker.api.dto.task.TaskDto;
import ewp.tasktracker.config.SchedulerProperties;
import ewp.tasktracker.config.TaskTrackerProperties;
import ewp.tasktracker.entity.common.Notification;
import ewp.tasktracker.service.bug.BugService;
import ewp.tasktracker.service.notificationSubscription.NotificationSubscriptionService;
import ewp.tasktracker.service.producerkafka.NotificationKafkaProducerService;
import ewp.tasktracker.service.task.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduler.enabled", matchIfMissing = true)
public class NotificationScheduler {
    private final SchedulerService service;
    private final BugService bugService;
    private final TaskService taskService;
    private final TaskTrackerProperties taskProperties;
    private final SchedulerProperties schedulerProperties;
    private final NotificationKafkaProducerService producerService;
    private final NotificationSubscriptionService notificationSubscriptionService;

    @Scheduled(fixedRateString = "${task-tracker.scheduler.interval}")
    public void findNewEntities() {
        int pageNumber = taskProperties.getPageDefaultNumber();
        LocalDateTime createdAfter;

        SchedulerInfo schedulerInfo = service.findById(schedulerProperties.getId());
        createdAfter = schedulerInfo == null ?
                LocalDateTime.parse(schedulerProperties.getStartTime())
                : schedulerInfo.getLastTriggerTime();

        List<NotificationSubscriptionDto> notificationSubscriptionDtos = notificationSubscriptionService
                .findAll(taskProperties.getPageMaxSize(), pageNumber);

        while (!notificationSubscriptionDtos.isEmpty()) {
            notificationSubscriptionDtos.forEach(notificationSubscriptionDto -> {
                List<BugDto> bugDtoPageDto = findBugs(notificationSubscriptionDto, createdAfter);
                List<TaskDto> taskDtoPageDto = findTasks(notificationSubscriptionDto, createdAfter);
                if (bugDtoPageDto != null) {
                    bugDtoPageDto.forEach(dto -> producerService.send(
                            new Notification(notificationSubscriptionDto.getUserId(),
                                    "Новый баг: " + dto.getName())));
                }
                if (taskDtoPageDto != null) {
                    taskDtoPageDto.forEach(dto -> producerService.send(
                            new Notification(notificationSubscriptionDto.getUserId(),
                                    "Новая задача: " + dto.getName())));
                }
            });

            notificationSubscriptionDtos = notificationSubscriptionService
                    .findAll(taskProperties.getPageMaxSize(), ++pageNumber);
        }

        service.save(new SchedulerInfo(schedulerProperties.getId(), LocalDateTime.now()));
    }

    public List<BugDto> findBugs(NotificationSubscriptionDto notificationSubscriptionDto, LocalDateTime createdAfter) {
        return bugService.findByProjectId(notificationSubscriptionDto.getProjectId(), createdAfter
                , taskProperties.getPageMaxSize(), taskProperties.getPageDefaultNumber()).getItems();
    }

    public List<TaskDto> findTasks(NotificationSubscriptionDto notificationSubscriptionDto, LocalDateTime createdAfter) {
        return taskService.findByProjectId(notificationSubscriptionDto.getProjectId(), createdAfter
                , taskProperties.getPageMaxSize(), taskProperties.getPageDefaultNumber()).getItems();
    }
}
