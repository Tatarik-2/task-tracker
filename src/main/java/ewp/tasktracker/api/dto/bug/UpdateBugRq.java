package ewp.tasktracker.api.dto.bug;

import com.sun.istack.NotNull;
import ewp.tasktracker.entity.BugEntity;
import ewp.tasktracker.entity.common.Priority;
import ewp.tasktracker.entity.common.ProgressStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBugRq {
    @NotNull
    private String id;
    @NotNull
    @Size(max = 128)
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

    public BugEntity updateEntity(BugEntity entityFromDB) {
        BugEntity bugEntity = new BugEntity();
        bugEntity.setId(entityFromDB.getId());
        bugEntity.setName(this.getName());
        bugEntity.setDescription(this.getDescription());
        bugEntity.setStatus(this.getStatus());
        bugEntity.setPriority(this.getPriority());
        bugEntity.setHistoryId(this.getHistoryId());
        bugEntity.setAuthorId(this.getAuthorId());
        bugEntity.setAssigneeId(this.getAssigneeId());
        bugEntity.setSprintId(this.getSprintId());
        bugEntity.setCreatedAt(entityFromDB.getCreatedAt());
        return bugEntity;
    }
}
