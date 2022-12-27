package ewp.tasktracker.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ewp.tasktracker.entity.common.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "projects")
@Table(name = "projects")
public class ProjectEntity extends BaseEntity {
    private String name;
    private String description;
    private String status;
    private String authorId;

    private String workloadId;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public ProjectEntity(String name, String description, String status, String authorId, String workloadId) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.authorId = authorId;
        this.workloadId = workloadId;
    }

}
