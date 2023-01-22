package ewp.tasktracker.entity;

import ewp.tasktracker.entity.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "sprint")
public class SprintEntity extends BaseEntity {
    private String name;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String authorId;
    private String superSprintId;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public SprintEntity(String name, LocalDateTime startAt, LocalDateTime endAt, String authorId, String superSprintId) {
        this.name = name;
        this.startAt = startAt;
        this.endAt = endAt;
        this.authorId = authorId;
        this.superSprintId = superSprintId;
    }
}
