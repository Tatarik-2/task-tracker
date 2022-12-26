package ewp.tasktracker.entity;
import ewp.tasktracker.entity.common.Priority;
import ewp.tasktracker.entity.common.ProgressStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tasks")
public class TaskEntity extends BaseEntity {
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private ProgressStatus status;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    private String historyId;
    private String authorId;
    private String assigneeId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
