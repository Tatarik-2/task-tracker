package ewp.tasktracker.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "histories")
public class HistoryEntity extends BaseEntity {

    private String name;

    private String description;

    private String status; //TODO, IN PROGRESS, DONE - реализовать через ENUM ?

    private String priority; //TODO LOW, MEDIUM, HIGH - реализовать ENUM

    private String epicId;

    private String authorId;

    private String sprintId;
    // в будущем вынести в BaseEntity
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public HistoryEntity(String name, String description, String status, String priority,
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
