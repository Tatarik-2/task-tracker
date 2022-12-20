package ewp.tasktracker.api.dto;

import ewp.tasktracker.entity.HistoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryDto {
    private String id;
    @NotEmpty
    @Size(max = 128)
    private String name;
    @NotEmpty
    @Size(max = 256)
    private String description;
    @NotEmpty
    @Size(max = 18)
    private String status; //TODO, IN PROGRESS, DONE - реализовать через ENUM ?
    @NotEmpty
    @Size(max = 18)
    private String priority; //TODO LOW, MEDIUM, HIGH - реализовать ENUM
    @NotEmpty
    @Size(max = 36)
    private String epicId;
    @NotEmpty
    @Size(max = 36)
    private String authorId;
    @NotEmpty
    @Size(max = 36)
    private String sprintId;

    public HistoryDto(HistoryEntity historyEntity) {
        this.id = historyEntity.getId();
        this.name = historyEntity.getName();
        this.description = historyEntity.getDescription();
        this.status = historyEntity.getStatus();
        this.priority = historyEntity.getPriority();
        this.epicId = historyEntity.getEpicId();
        this.authorId = historyEntity.getAuthorId();
        this.sprintId = historyEntity.getSprintId();
    }
}
