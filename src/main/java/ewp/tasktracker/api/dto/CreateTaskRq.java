package ewp.tasktracker.api.dto;

import ewp.tasktracker.entity.TaskEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskRq {

    private String name;
    private String desc;

    private String status;
    private String priority;
    private String history_id;
    private String author_id;
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
