package ewp.tasktracker.service.producerkafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ewp.tasktracker.entity.common.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationKafkaProducerService {
    @Value("${topic.name}")
    private String topic;

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;


    @Autowired
    public NotificationKafkaProducerService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void send(Notification notification) {
        String notificationAsMessage = null;
        try {
            notificationAsMessage = objectMapper.writeValueAsString(notification);
        } catch (JsonProcessingException e) {
            log.error("task-tracker kafka produced {}", e);
        }
        kafkaTemplate.send(topic, notificationAsMessage);
        log.info("task-tracker kafka produced {}", notificationAsMessage);
    }
}
