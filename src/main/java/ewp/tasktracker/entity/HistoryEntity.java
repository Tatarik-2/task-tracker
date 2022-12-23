package ewp.tasktracker.entity;


import ewp.tasktracker.entity.common.Priority;
import ewp.tasktracker.entity.common.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "histories")
public class HistoryEntity extends BaseEntity {

    private String name;

    private String description;

    private String status;

    private String priority;

    private String epicId;

    private String authorId;

    private String sprintId;
    // в будущем вынести в BaseEntity
    @CreationTimestamp
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    public HistoryEntity(String name, String description, Status status, Priority priority,
                         String epicId, String authorId, String sprintId) {
        this.name = name;
        this.description = description;
        this.status = status.toString();
        this.priority = priority.toString();
        this.epicId = epicId;
        this.authorId = authorId;
        this.sprintId = sprintId;
    }
}
