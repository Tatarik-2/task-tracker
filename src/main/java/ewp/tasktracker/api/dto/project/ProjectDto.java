package ewp.tasktracker.api.dto.project;

import ewp.tasktracker.entity.ProjectEntity;
import ewp.tasktracker.entity.common.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private String id;
    private String name;
    private String description;
    private Status status;
    private String authorId;
    private String workloadId;

    public ProjectDto(ProjectEntity projectEntity) {
        this.id = projectEntity.getId();
        this.name = projectEntity.getName();
        this.description = projectEntity.getDescription();
        this.status = projectEntity.getStatus();
        this.authorId = projectEntity.getAuthorId();
        this.workloadId = projectEntity.getWorkloadId();
    }
}
