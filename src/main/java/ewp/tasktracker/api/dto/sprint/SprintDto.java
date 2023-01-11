package ewp.tasktracker.api.dto.sprint;

import ewp.tasktracker.entity.SprintEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SprintDto {
    private String id;
    private String name;
    private String authorId;
    private String superSprintId;

    public SprintDto(SprintEntity sprintEntity) {
        this.id = sprintEntity.getId();
        this.name = sprintEntity.getName();
        this.authorId = sprintEntity.getAuthorId();
        this.superSprintId = sprintEntity.getSuperSprintId();
    }
}
