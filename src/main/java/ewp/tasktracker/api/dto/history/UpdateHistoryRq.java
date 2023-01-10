package ewp.tasktracker.api.dto.history;

import ewp.tasktracker.entity.HistoryEntity;
import ewp.tasktracker.entity.common.Priority;
import ewp.tasktracker.entity.common.ProgressStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateHistoryRq {
    private String id;
    @NotEmpty
    @Size(max = 128)
    private String name;
    @NotEmpty
    @Size(max = 256)
    private String description;
    private ProgressStatus status;
    private Priority priority;
    @NotEmpty
    @Size(max = 36)
    private String epicId;
    @NotEmpty
    @Size(max = 36)
    private String authorId;
    @Size(max = 36)
    private String sprintId;

    public HistoryEntity updateEntity(HistoryEntity entityFromDB) {
        HistoryEntity historyEntity = new HistoryEntity();
        historyEntity.setId(entityFromDB.getId());
        historyEntity.setCreatedAt(entityFromDB.getCreatedAt());
        historyEntity.setName(this.getName());
        historyEntity.setDescription(this.getDescription());
        historyEntity.setStatus(this.getStatus());
        historyEntity.setPriority(this.getPriority());
        historyEntity.setEpicId(this.getEpicId());
        historyEntity.setAuthorId(this.getAuthorId());
        historyEntity.setSprintId(this.getSprintId());
        return historyEntity;
    }
}
