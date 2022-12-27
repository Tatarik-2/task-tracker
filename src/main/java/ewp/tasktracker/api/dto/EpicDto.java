package ewp.tasktracker.api.dto;

import ewp.tasktracker.entity.EpicEntity;
import ewp.tasktracker.entity.common.Priority;
import ewp.tasktracker.entity.common.ProgressStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EpicDto {

    private String id;
    private String name;
    private String description;
    private ProgressStatus status;
    private Priority priority;
    private String projectId;
    private String authorId;
    private String supersprintId;


    public EpicDto(EpicEntity epicEntity) {
        this.id = epicEntity.getId();
        this.name = epicEntity.getName();
        this.description = epicEntity.getDescription();
        this.status = epicEntity.getStatus();
        this.priority = epicEntity.getPriority();
        this.projectId = epicEntity.getProjectId();
        this.authorId = epicEntity.getAuthorId();
        this.supersprintId = epicEntity.getSupersprintId();
    }
}
