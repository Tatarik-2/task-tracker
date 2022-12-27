package ewp.tasktracker.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "labels")
public class LabelsEntity extends BaseEntity {
    private String text;
    private String authorId;
    private String taskId;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
