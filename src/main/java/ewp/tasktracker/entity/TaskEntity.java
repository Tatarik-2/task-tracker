package ewp.tasktracker.entity;

import ewp.tasktracker.entity.common.Priority;
import ewp.tasktracker.entity.common.ProgressStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tasks")
public class TaskEntity extends BaseEntity {

    private String name;
    private String DESCRIPTION;
    private ProgressStatus status;
    private Priority priority;
    private String history_id;
    private String author_id;
    private String assignee_id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

}
