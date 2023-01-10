package ewp.tasktracker.entity;

import ewp.tasktracker.entity.common.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ewp.tasktracker.entity.common.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "projects")
@Table(name = "projects")
public class ProjectEntity extends BaseEntity {
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String authorId;

    private String workloadId;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public ProjectEntity(String name, String description, Status status, String authorId, String workloadId) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.authorId = authorId;
        this.workloadId = workloadId;
    }

}
