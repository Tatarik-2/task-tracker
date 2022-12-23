package ewp.tasktracker.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity extends BaseEntity {
    private String text;
    @Column(name = "task_id")
    private String taskId;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAd;
    public CommentEntity(String text, String taskId) {
        this.text = text;
        this.taskId = taskId;
    }
}
