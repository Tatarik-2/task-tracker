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
public class CreateHistoryRq {

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

    public HistoryEntity toEntity() {
        return new HistoryEntity(this.name, this.description, this.status,
                this.priority, this.epicId, this.authorId, this.sprintId);
    }
}
