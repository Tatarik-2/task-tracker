package ewp.tasktracker.api.dto;

import com.sun.istack.NotNull;
import ewp.tasktracker.entity.BugEntity;
import ewp.tasktracker.service.PriorityEnum;
import ewp.tasktracker.service.StatusEnum2;
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
    private String desc;
    private StatusEnum2 status;
    private PriorityEnum priority;
    @NotNull
    @Size(max = 36)
    private String historyId;
    @NotNull
    @Size(max = 36)
    private String authorId;
    @NotNull
    @Size(max = 36)
    private String assigneeId;

    public BugEntity toEntity() {
        return new BugEntity(
                this.name, this.desc, this.status.toString(), this.priority.toString(),
                this.historyId, this.authorId, this.assigneeId,
                LocalDate.now(), LocalDate.now());
    }
}
