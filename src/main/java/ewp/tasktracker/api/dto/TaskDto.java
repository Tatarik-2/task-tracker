package ewp.tasktracker.api.dto;

import ewp.tasktracker.entity.TaskEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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

    public TaskDto(TaskEntity taskEntity) {
        this.id = taskEntity.getId();
        this.name = taskEntity.getName();
        this.desc = taskEntity.getDesc();
        this.status = taskEntity.getStatus();
        this.priority = taskEntity.getPriority();
        this.history_id = taskEntity.getHistory_id();
        this.author_id = taskEntity.getAuthor_id();
    }
}
