package ewp.tasktracker.api.dto;

import ewp.tasktracker.entity.TaskEntity;
import ewp.tasktracker.entity.common.Priority;
import ewp.tasktracker.entity.common.ProgressStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskRq {
    @NotNull(message = "Id не должно быть null")
    private String id;
    @NotEmpty
    @Size(min = 3, max = 128)
    private String name;
    @NotEmpty
    @Size(min = 3, max = 256)
    private String description;
    private ProgressStatus status;
    private Priority priority;
    @NotEmpty
    @Size(min = 3, max = 36)
    private String historyId;
    @NotEmpty
    @Size(min = 3, max = 36)
    private String authorId;
    @Size(min = 3, max = 36)
    private String assigneeId;

    public TaskEntity updateEntity(TaskEntity entityFromDB, UpdateTaskRq updateTaskRq) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(entityFromDB.getId());
        taskEntity.setCreatedAt(entityFromDB.getCreatedAt());
        taskEntity.setName(updateTaskRq.getName());
        taskEntity.setDescription(updateTaskRq.getDescription());
        taskEntity.setStatus(updateTaskRq.getStatus());
        taskEntity.setPriority(updateTaskRq.getPriority());
        taskEntity.setHistoryId(updateTaskRq.getHistoryId());
        taskEntity.setAuthorId(updateTaskRq.getAuthorId());
        taskEntity.setAssigneeId(updateTaskRq.getAssigneeId());
        return taskEntity;
    }
}