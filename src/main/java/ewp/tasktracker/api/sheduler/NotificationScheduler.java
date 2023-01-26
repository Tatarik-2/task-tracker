package ewp.tasktracker.api.sheduler;

import ewp.tasktracker.api.dto.notificationSubscription.NotificationSubscriptionDto;
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
import java.util.ArrayList;
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
        List<NotificationSubscriptionDto> notificationSubscriptionDtos = new ArrayList<>();
        List<NotificationSubscriptionDto> notificationSubscription = notificationSubscriptionService
                .findAll(taskProperties.getPageMaxSize(), pageNumber);
        while (!notificationSubscription.isEmpty()) {
            notificationSubscriptionDtos.addAll(notificationSubscription);
            notificationSubscription = notificationSubscriptionService
                    .findAll(taskProperties.getPageMaxSize(), ++pageNumber);
        }

        LocalDateTime createdAfter;

        SchedulerInfo schedulerInfo = service.findById(schedulerProperties.getId());
        createdAfter = schedulerInfo == null ?
                LocalDateTime.parse(schedulerProperties.getStartTime())
                : schedulerInfo.getLastTriggerTime();

        notificationSubscriptionDtos.forEach(notificationSubscriptionDto ->
        {
            if (bugService.findByProjectId(notificationSubscriptionDto.getProjectId(), createdAfter
                    , taskProperties.getPageMaxSize(), taskProperties.getPageDefaultNumber()).getItems() != null) {
                bugService.findByProjectId(notificationSubscriptionDto.getProjectId(), createdAfter
                                , taskProperties.getPageMaxSize(), taskProperties.getPageDefaultNumber())
                        .getItems().forEach(bugDto -> producerService.send(
                                new Notification(notificationSubscriptionDto.getUserId(),
                                        "Новый баг: " + bugDto.getName())));
            }
            if (taskService.findByProjectId(notificationSubscriptionDto.getProjectId(), createdAfter
                    , taskProperties.getPageMaxSize(), taskProperties.getPageDefaultNumber()).getItems() != null) {
                taskService.findByProjectId(notificationSubscriptionDto.getProjectId(), createdAfter
                                , taskProperties.getPageMaxSize(), taskProperties.getPageDefaultNumber())
                        .getItems().forEach(taskDto -> producerService.send(
                                new Notification(notificationSubscriptionDto.getUserId(),
                                        "Новая задача: " + taskDto.getName())));
            }
        });

        service.save(new SchedulerInfo(schedulerProperties.getId(), LocalDateTime.now()));
    }
}
