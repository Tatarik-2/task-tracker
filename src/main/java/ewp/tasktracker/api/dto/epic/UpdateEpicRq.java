package ewp.tasktracker.api.dto.epic;

import ewp.tasktracker.entity.EpicEntity;
import ewp.tasktracker.entity.common.Priority;
import ewp.tasktracker.entity.common.ProgressStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEpicRq {

    @Size(max = 128)
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
    private String projectId;

    @NotEmpty
    @Size(max = 36)
    private String authorId;

    @NotEmpty
    @Size(max = 36)
    private String supersprintId;

    public EpicEntity toEntity(EpicEntity epicEntityFromDb, UpdateEpicRq updateEpicRq) {
        EpicEntity updatedEpicEnity = new EpicEntity();
        updatedEpicEnity.setId(epicEntityFromDb.getId());
        updatedEpicEnity.setName(updateEpicRq.getName());
        updatedEpicEnity.setDescription(updateEpicRq.getDescription());
        updatedEpicEnity.setStatus(updateEpicRq.getStatus());
        updatedEpicEnity.setPriority(updateEpicRq.getPriority());
        updatedEpicEnity.setProjectId(updateEpicRq.getProjectId());
        updatedEpicEnity.setAuthorId(updateEpicRq.getAuthorId());
        updatedEpicEnity.setSupersprintId(updateEpicRq.getSupersprintId());
        updatedEpicEnity.setCreatedAt(epicEntityFromDb.getCreatedAt());
        return updatedEpicEnity;
    }



}
