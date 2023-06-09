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
public class CreateProjectRq {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    private Status status;
    @NotBlank
    private String authorId;
    @NotBlank
    private String workloadId;

    public ProjectEntity toEntity() {
        return new ProjectEntity(
                this.name,
                this.description,
                this.status,
                this.authorId,
                this.workloadId
        );
    }
}
