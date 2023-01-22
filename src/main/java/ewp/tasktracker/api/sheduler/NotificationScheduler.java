package ewp.tasktracker.api.sheduler;

import ewp.tasktracker.api.kafka.Notification;
import ewp.tasktracker.api.kafka.NotificationKafkaProducerService;
import ewp.tasktracker.config.SchedulerProperties;
import ewp.tasktracker.config.TaskTrackerProperties;
import ewp.tasktracker.service.bug.BugService;
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
    private final SchedulerProperties properties;
    private final NotificationKafkaProducerService producerService;
    private final NotificationSubscriptionService notificationSubscriptionService;//TODO еще не реализовано

    @Scheduled(fixedRateString = "${scheduler.interval}")
    public void findNewEntities() {
        List<NotificationSubscriptionDto> notificationSubscriptionDtos = notificationSubscriptionService
                .findAll(taskProperties.getPageMaxSize(), taskProperties.getPageDefaultNumber());
        //TODO как выбрать все уведомления, а не только из нулевого пага?

        LocalDateTime createdAfter;

        SchedulerInfo schedulerInfo = service.findById(properties.getId());
        //шедулер у нас только один, с известным изначально айдишником
        createdAfter = schedulerInfo == null ?
                LocalDateTime.parse(properties.getStartTime())
                : schedulerInfo.getLastTriggerTime();

        notificationSubscriptionDtos.forEach(notificationSubscriptionDto -> bugService
                .findByProjectId(notificationSubscriptionDto.getProjectId(), createdAfter
                        , taskProperties.getPageMaxSize(), taskProperties.getPageDefaultNumber())
                .getItems().forEach(bugDto -> producerService.send(
                        new Notification("Новый баг: " + bugDto.getName()
                                , notificationSubscriptionDto.getUserId()))));

        notificationSubscriptionDtos.forEach(notificationSubscriptionDto -> taskService
                .findByProjectId(notificationSubscriptionDto.getProjectId(), createdAfter
                        , taskProperties.getPageMaxSize(), taskProperties.getPageDefaultNumber())
                .getItems().forEach(taskDto -> producerService.send(
                        new Notification("Новая задача: " + taskDto.getName()
                                , notificationSubscriptionDto.getUserId()))));

        service.save(new SchedulerInfo(properties.getId(), LocalDateTime.now()));
    }
}
