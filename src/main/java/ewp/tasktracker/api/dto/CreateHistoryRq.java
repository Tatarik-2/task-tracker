package ewp.tasktracker.api.dto;

import ewp.tasktracker.entity.HistoryEntity;
import ewp.tasktracker.service.PriorityEnum;
import ewp.tasktracker.service.StatusEnum2;
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
    @Size(min = 2, max = 128)
    private String name;
    @NotEmpty
    @Size(max = 256)
    private String description;
    private StatusEnum2 status;
    private PriorityEnum priority;
    @NotEmpty
    @Size(max = 36)
    private String epicId;
    @NotEmpty
    @Size(max = 36)
    private String authorId;
    @NotEmpty
    @Size(max = 36)
    private String sprintId;

    public HistoryEntity toEntity() {
        return new HistoryEntity(this.name, this.description, this.status,
                this.priority, this.epicId, this.authorId, this.sprintId);
    }
}
