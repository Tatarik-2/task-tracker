package ewp.tasktracker.api.dto;

import ewp.tasktracker.entity.BugEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BugDto {
    private String name;
    private String desc;
    private String status;
    private String priority;
    private String historyId;
    private String authorId;
    private String assigneeId;
    private String sprintId;

    public BugDto(BugEntity bugEntity) {
        this.name = bugEntity.getName();
        this.desc = bugEntity.getDesc();
        this.status = bugEntity.getStatus();
        this.priority = bugEntity.getPriority();
        this.historyId = bugEntity.getHistoryId();
        this.authorId = bugEntity.getAuthorId();
        this.assigneeId = bugEntity.getAssigneeId();
        this.sprintId = bugEntity.getSprintId();
    }
}
