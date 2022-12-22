package ewp.tasktracker.entity;


import ewp.tasktracker.service.PriorityEnum;
import ewp.tasktracker.service.StatusEnum2;
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

    public HistoryEntity(String name, String description, StatusEnum2 status, PriorityEnum priority,
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
