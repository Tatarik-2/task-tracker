package ewp.tasktracker.entity;

import ewp.tasktracker.api.dto.CreateCommentRq;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAd;
    @Column(name = "task_id")
    private String taskId;
    public CommentEntity(CreateCommentRq entity) {
        this.text = entity.getText();
        this.taskId = entity.getTaskId();
    }
}
