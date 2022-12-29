package ewp.tasktracker.entity;

import ewp.tasktracker.entity.common.BaseEntity;
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
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "bugs")
public class BugEntity extends BaseEntity {
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private ProgressStatus status;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    private String historyId;
    private String authorId;
    private String assigneeId;
    private String sprintId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
