package ewp.tasktracker.api.dto;

import com.sun.istack.NotNull;
import ewp.tasktracker.entity.BugEntity;
import ewp.tasktracker.entity.common.Priority;
import ewp.tasktracker.entity.common.ProgressStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBugRq {
    @NotNull
    @Size(min = 2, max = 128)
    private String name;
    @NotNull
    @Size(max = 256)
    private String description;
    private ProgressStatus status;
    private Priority priority;
    @NotNull
    @Size(max = 36)
    private String historyId;
    @NotNull
    @Size(max = 36)
    private String authorId;
    @NotNull
    @Size(max = 36)
    private String assigneeId;
    @Size(max = 36)
    private String sprintId;

    public BugEntity toEntity() {
        return new BugEntity(
                this.name, this.description, this.status, this.priority,
                this.historyId, this.authorId, this.assigneeId, this.sprintId,
                LocalDateTime.now(), LocalDateTime.now());
    }
}
