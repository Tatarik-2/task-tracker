package ewp.tasktracker.api.dto;

import ewp.tasktracker.entity.EpicEntity;
import ewp.tasktracker.service.PriorityEnum;
import ewp.tasktracker.service.Status2Enum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEpicRq {
    @NotEmpty
    @Size(max=128)
    private String name;
    @NotEmpty
    @Size(max=256)
    private String description;


//    ------------No validator could be found for constraint
//    'javax.validation.constraints.NotEmpty' validating type 'ewp.tasktracker.service.Status2Enum'.
//    Check configuration for 'status'

//    @NotEmpty
//    @Size(max=18)
    private Status2Enum status;
//    @NotEmpty
//    @Size(max=18)
    private PriorityEnum priority;
    @NotEmpty
    @Size(max=36)
    private String projectId;
    @NotEmpty
    @Size(max=36)
    private String authorId;
    @NotEmpty
    @Size(max=36)
    private String supersprintId;

    public EpicEntity toEntity() {
        return new EpicEntity(
                this.name,
                this.description,
                this.status,
                this.priority,
                this.projectId,
                this.authorId,
                this.supersprintId
        );
    }

}
