package ewp.tasktracker.api.dto.history;

import ewp.tasktracker.entity.HistoryEntity;
import ewp.tasktracker.entity.common.Priority;
import ewp.tasktracker.entity.common.ProgressStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    public HistoryEntity updateEntity(HistoryEntity entityFromDB, UpdateHistoryRq updateHistoryRq) {
        HistoryEntity historyEntity = new HistoryEntity();
        historyEntity.setId(entityFromDB.getId());
        historyEntity.setCreatedAt(entityFromDB.getCreatedAt());
        historyEntity.setName(updateHistoryRq.getName());
        historyEntity.setDescription(updateHistoryRq.getDescription());
        historyEntity.setStatus(updateHistoryRq.getStatus());
        historyEntity.setPriority(updateHistoryRq.getPriority());
        historyEntity.setEpicId(updateHistoryRq.getEpicId());
        historyEntity.setAuthorId(updateHistoryRq.getAuthorId());
        historyEntity.setSprintId(updateHistoryRq.getSprintId());
        return historyEntity;
    }
}
