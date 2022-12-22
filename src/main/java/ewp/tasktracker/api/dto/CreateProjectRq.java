package ewp.tasktracker.api.dto;

import ewp.tasktracker.entity.ProjectEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProjectRq {
    private String name;
    protected String desc;
    private String status;
    private String authorId;
    private String workloadId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProjectEntity toEntity() {
        return new ProjectEntity(
                this.name,
                this.desc,
                this.status,
                this.authorId,
                this.workloadId,
                this.createdAt,
                this.updatedAt
        );
    }
}
