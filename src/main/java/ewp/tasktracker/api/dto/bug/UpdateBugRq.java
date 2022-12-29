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

    public BugEntity updateEntity(BugEntity entityFromDB, UpdateBugRq updateBugRq) {
        BugEntity bugEntity = new BugEntity();
        bugEntity.setId(entityFromDB.getId());
        bugEntity.setName(updateBugRq.getName());
        bugEntity.setDescription(updateBugRq.getDescription());
        bugEntity.setStatus(updateBugRq.getStatus());
        bugEntity.setPriority(updateBugRq.getPriority());
        bugEntity.setHistoryId(updateBugRq.getHistoryId());
        bugEntity.setAuthorId(updateBugRq.getAuthorId());
        bugEntity.setAssigneeId(updateBugRq.getAssigneeId());
        bugEntity.setSprintId(updateBugRq.getSprintId());
        bugEntity.setCreatedAt(entityFromDB.getCreatedAt());
        return bugEntity;
    }
}
