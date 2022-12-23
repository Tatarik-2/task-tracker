package ewp.tasktracker.entity;

import ewp.tasktracker.entity.common.BaseEntity;
import ewp.tasktracker.entity.common.Priority;
import ewp.tasktracker.entity.common.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "epics")
public class EpicEntity extends BaseEntity {

    private String name;

    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @Column(name = "project_id")
    private String projectId;
    @Column(name = "author_id")
    private String authorId;
    @Column(name = "supersprint_id")
    private String supersprintId;

//    BaseEntity
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    public EpicEntity(String name, String description,
                      Status status, Priority priority,
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
