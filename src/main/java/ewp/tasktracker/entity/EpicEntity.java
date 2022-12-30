package ewp.tasktracker.entity;

import ewp.tasktracker.entity.common.BaseEntity;
import ewp.tasktracker.entity.common.Priority;
import ewp.tasktracker.entity.common.ProgressStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "epics")
public class EpicEntity extends BaseEntity {

    private String name;

    private String description;
    @Enumerated(EnumType.STRING)
    private ProgressStatus status;
    @Enumerated(EnumType.STRING)
    private Priority priority;

    private String projectId;

    private String authorId;

    private String supersprintId;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


    public EpicEntity(String name, String description,
                      ProgressStatus status, Priority priority,
                      String projectId, String authorId, String supersprintId) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.projectId = projectId;
        this.authorId = authorId;
        this.supersprintId = supersprintId;

    }
}
