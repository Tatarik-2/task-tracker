package ewp.tasktracker.entity;


import ewp.tasktracker.entity.common.Priority;
import ewp.tasktracker.entity.common.ProgressStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "histories")
public class HistoryEntity extends BaseEntity {

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private ProgressStatus status;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private String epicId;

    private String authorId;

    private String sprintId;
    // в будущем вынести в BaseEntity
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public HistoryEntity(String name, String description, ProgressStatus status, Priority priority,
                         String epicId, String authorId, String sprintId) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.epicId = epicId;
        this.authorId = authorId;
        this.sprintId = sprintId;
    }
}
