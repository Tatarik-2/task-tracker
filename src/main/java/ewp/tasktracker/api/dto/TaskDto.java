package ewp.tasktracker.api.dto;

import ewp.tasktracker.entity.TaskEntity;
import ewp.tasktracker.entity.common.Priority;
import ewp.tasktracker.entity.common.ProgressStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private String id;
    private String name;
    private String description;

    private ProgressStatus status;
    private Priority priority;
    private String historyId;
    private String authorId;
    private String assigneeId;

    public TaskDto(TaskEntity taskEntity) {
        this.id = taskEntity.getId();
        this.name = taskEntity.getName();
        this.description = taskEntity.getDescription();
        this.status = taskEntity.getStatus();
        this.priority = taskEntity.getPriority();
        this.historyId = taskEntity.getHistoryId();
        this.authorId = taskEntity.getAuthorId();
    }

}
