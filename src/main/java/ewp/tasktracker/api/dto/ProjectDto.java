package ewp.tasktracker.api.dto;

import ewp.tasktracker.entity.ProjectEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private String name;
    private String description;
    private String status;
    private String authorId;
    private String workloadId;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    public ProjectDto(ProjectEntity projectEntity) {
        this.name = projectEntity.getName();
        this.description = projectEntity.getDescription();
        this.status = projectEntity.getStatus();
        this.authorId = projectEntity.getAuthorId();
        this.workloadId = projectEntity.getWorkloadId();
    }
}
