package ewp.tasktracker.api.dto;

import ewp.tasktracker.entity.HistoryEntity;
import ewp.tasktracker.entity.common.Priority;
import ewp.tasktracker.entity.common.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryDto {
    private String id;
    private String name;
    private String description;
    private Status status;
    private Priority priority;
    private String epicId;
    private String authorId;
    private String sprintId;

    public HistoryDto(HistoryEntity historyEntity) {
        this.id = historyEntity.getId();
        this.name = historyEntity.getName();
        this.description = historyEntity.getDescription();
        this.status = Status.valueOf(historyEntity.getStatus());
        this.priority = Priority.valueOf(historyEntity.getPriority());
        this.epicId = historyEntity.getEpicId();
        this.authorId = historyEntity.getAuthorId();
        this.sprintId = historyEntity.getSprintId();
    }
}
