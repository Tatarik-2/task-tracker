package ewp.tasktracker.api.dto;

import ewp.tasktracker.entity.TaskEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private String id;
    private String name;
    private String desc;

    private String status;
    private String priority;
    private String history_id;
    private String author_id;
    private String assignee_id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public TaskDto(TaskEntity taskEntity) {
        this.id = taskEntity.getId();
        this.name = taskEntity.getName();
        this.desc = taskEntity.getDesc();
        this.status = taskEntity.getStatus();
        this.priority = taskEntity.getPriority();
        this.history_id = taskEntity.getHistory_id();
        this.author_id = taskEntity.getAuthor_id();
        this.created_at = taskEntity.getCreated_at();
        this.updated_at = taskEntity.getUpdated_at();
    }
}
