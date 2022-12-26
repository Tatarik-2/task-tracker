package ewp.tasktracker.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
public class CommentEntity extends BaseEntity {
    private String text;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAd;
    @Column(name = "task_id")
    private String taskId;

    public CommentEntity(String text, String taskId) {
        this.text = text;
        this.taskId = taskId;
    }
}
