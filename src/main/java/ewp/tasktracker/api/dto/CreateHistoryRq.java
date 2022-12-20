package ewp.tasktracker.api.dto;

import ewp.tasktracker.entity.HistoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateHistoryRq {

    private String name;

    private String description;

    private String status; //TODO, IN PROGRESS, DONE - реализовать через ENUM ?

    private String priority; //TODO LOW, MEDIUM, HIGH - реализовать ENUM

    private String epicId;

    private String authorId;

    private String sprintId;

    public HistoryEntity toEntity() {
        return new HistoryEntity(this.name, this.description, this.status,
                this.priority, this.epicId, this.authorId, this.sprintId);
    }
}
