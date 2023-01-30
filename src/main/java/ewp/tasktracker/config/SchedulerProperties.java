package ewp.tasktracker.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "task-tracker.scheduler")
@Data
public class SchedulerProperties {
    private String id;
    private String startTime;
}

