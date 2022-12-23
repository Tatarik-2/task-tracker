package ewp.tasktracker.entity;

import ewp.tasktracker.service.PriorityEnum;
import ewp.tasktracker.service.Status2Enum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "epics")
public class EpicEntity extends BaseEntity {

    private String name;

    private String description;

    private String status;

    private String priority;
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

// Будут ли enum в базу сохраяться? Или надо в String
    public EpicEntity(String name, String description,
                      Status2Enum status, PriorityEnum priority,
                      String projectId, String authorId, String supersprintId) {
        this.name = name;
        this.description = description;
        this.status = status.toString();
        this.priority = priority.toString();
        this.projectId = projectId;
        this.authorId = authorId;
        this.supersprintId = supersprintId;

    }
}
