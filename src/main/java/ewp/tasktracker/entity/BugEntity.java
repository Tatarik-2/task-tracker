package ewp.tasktracker.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "bugs")
public class BugEntity extends BaseEntity {
    private String name;
    private String description;
    private String status;
    private String priority;
    private String historyId;
    private String authorId;
    private String assigneeId;
    private String sprintId;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
