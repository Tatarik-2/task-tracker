package ewp.tasktracker.api.dto;

import ewp.tasktracker.entity.ProjectEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String desc;
    private String status;
    private String authorId;
    private String workloadId;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    public ProjectDto(ProjectEntity projectEntity) {
        this.id = projectEntity.getId();
        this.name = projectEntity.getName();
        this.desc = projectEntity.getDesc();
        this.status = projectEntity.getStatus();
        this.authorId = projectEntity.getAuthorId();
        this.workloadId = projectEntity.getWorkloadId();
        this.createdAt = projectEntity.getCreatedAt();
        this.updateAt = projectEntity.getUpdateAt();
    }
}
