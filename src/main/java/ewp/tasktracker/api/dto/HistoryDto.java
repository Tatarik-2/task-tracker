package ewp.tasktracker.api.dto;

import ewp.tasktracker.entity.HistoryEntity;
import ewp.tasktracker.service.PriorityEnum;
import ewp.tasktracker.service.StatusEnum2;
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
    private StatusEnum2 status;
    private PriorityEnum priority;
    private String epicId;
    private String authorId;
    private String sprintId;

    public HistoryDto(HistoryEntity historyEntity) {
        this.id = historyEntity.getId();
        this.name = historyEntity.getName();
        this.description = historyEntity.getDescription();
        this.status = StatusEnum2.valueOf(historyEntity.getStatus());
        this.priority = PriorityEnum.valueOf(historyEntity.getPriority());
        this.epicId = historyEntity.getEpicId();
        this.authorId = historyEntity.getAuthorId();
        this.sprintId = historyEntity.getSprintId();
    }
}
