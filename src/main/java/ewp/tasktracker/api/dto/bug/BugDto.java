package ewp.tasktracker.api.dto.bug;

import ewp.tasktracker.entity.BugEntity;
import ewp.tasktracker.entity.common.Priority;
import ewp.tasktracker.entity.common.ProgressStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BugDto {
    private String id;
    private String name;
    private String description;
    private ProgressStatus status;
    private Priority priority;
    private String historyId;
    private String authorId;
    private String assigneeId;
    private String sprintId;

    public BugDto(BugEntity bugEntity) {
        this.id = bugEntity.getId();
        this.name = bugEntity.getName();
        this.description = bugEntity.getDescription();
        this.status = bugEntity.getStatus();
        this.priority = bugEntity.getPriority();
        this.historyId = bugEntity.getHistoryId();
        this.authorId = bugEntity.getAuthorId();
        this.assigneeId = bugEntity.getAssigneeId();
        this.sprintId = bugEntity.getSprintId();
    }
}
