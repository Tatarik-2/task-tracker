package ewp.tasktracker.api.dto.task;

import ewp.tasktracker.entity.TaskEntity;
import ewp.tasktracker.entity.common.Priority;
import ewp.tasktracker.entity.common.ProgressStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskRq {
    @NotNull
    @Size(min = 3, max = 128)
    private String name;
    @NotNull
    @Size(min = 3, max = 256)
    private String description;
    @NotNull
    private ProgressStatus status;
    private Priority priority;
    @NotNull
    @Size(min = 3, max = 36)
    private String historyId;
    @NotNull
    @Size(min = 3, max = 36)
    private String authorId;
    @NotNull
    @Size(min = 3, max = 36)
    private String assigneeId;

    public TaskEntity toEntity() {
        return new TaskEntity(
                this.name,
                this.description,
                this.status,
                this.priority,
                this.historyId,
                this.authorId,
                this.assigneeId,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
