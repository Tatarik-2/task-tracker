package ewp.tasktracker.api.dto.project;

import ewp.tasktracker.entity.ProjectEntity;
import ewp.tasktracker.entity.common.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProjectRq {
    @NotBlank
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    private Status status;
    @NotBlank
    private String authorId;
    @NotBlank
    private String workloadId;

    public ProjectEntity updateProject(ProjectEntity projectEntityFromDB) {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(projectEntityFromDB.getId());
        projectEntity.setCreatedAt(projectEntityFromDB.getCreatedAt());
        projectEntity.setName(this.getName());
        projectEntity.setDescription(this.getDescription());
        projectEntity.setStatus(this.getStatus());
        projectEntity.setAuthorId(this.getAuthorId());
        projectEntity.setWorkloadId(this.getWorkloadId());
        return projectEntity;
    }
}
