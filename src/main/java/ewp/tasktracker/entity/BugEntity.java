package ewp.tasktracker.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "bugs")
public class BugEntity extends BaseEntity {
    private String name;
    @Column(name = "description")
    private String desc;
    private String status;
    private String priority;
    @Column(name = "history_id")
    private String historyId;
    @Column(name = "author_id")
    private String authorId;
    @Column(name = "assignee_id")
    private String assigneeId;
    @Column(name = "sprint_id")
    private String sprintId;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @Column(name = "updated_at")
    private LocalDate updatedAt;
}
