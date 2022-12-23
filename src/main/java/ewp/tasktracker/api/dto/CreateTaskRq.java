package ewp.tasktracker.api.dto;

import ewp.tasktracker.entity.TaskEntity;
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
    private String desc;
    @NotNull
    private String status;
    private String priority;
    @NotNull
    @Size(min = 3, max = 36)
    private String history_id;
    @NotNull
    @Size(min = 3, max = 36)
    private String author_id;
    @NotNull
    @Size(min = 3, max = 36)
    private String assignee_id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public TaskEntity toEntity() {
        return new TaskEntity(
                this.name,
                this.desc,
                this.status,
                this.priority,
                this.history_id,
                this.author_id,
                this.assignee_id,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
