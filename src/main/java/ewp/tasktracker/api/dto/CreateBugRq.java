package ewp.tasktracker.api.dto;

import com.sun.istack.NotNull;
import ewp.tasktracker.entity.BugEntity;
import ewp.tasktracker.entity.common.Priority;
import ewp.tasktracker.entity.common.ProgressStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.time.LocalDate;

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
    private ProgressStatus progressStatus;
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
    @NotNull
    @Size(max = 36)
    private String sprintId;

    public BugEntity toEntity() {
        return new BugEntity(
                this.name, this.description, this.progressStatus.toString(), this.priority.toString(),
                this.historyId, this.authorId, this.assigneeId, this.sprintId,
                LocalDate.now(), LocalDate.now());
    }
}
