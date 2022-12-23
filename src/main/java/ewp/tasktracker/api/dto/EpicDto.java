package ewp.tasktracker.api.dto;

import ewp.tasktracker.entity.EpicEntity;
import ewp.tasktracker.service.PriorityEnum;
import ewp.tasktracker.service.Status2Enum;
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
    private Status2Enum status;
    private PriorityEnum priority;
    private String projectId;
    private String authorId;
    private String supersprintId;

//    String и Enum пока не понятно
    public EpicDto(EpicEntity epicEntity) {
        this.id = epicEntity.getId();
        this.name = epicEntity.getName();
        this.description = epicEntity.getDescription();
        this.status = Status2Enum.valueOf(epicEntity.getStatus());
        this.priority = PriorityEnum.valueOf(epicEntity.getPriority());
        this.projectId = epicEntity.getProjectId();
        this.authorId = epicEntity.getAuthorId();
        this.supersprintId = epicEntity.getSupersprintId();
    }
}
