package ewp.tasktracker.entity;

import ewp.tasktracker.entity.common.BaseEntity;
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
@Entity(name = "supersprints")
public class SupersprintEntity extends BaseEntity{

    private String name;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String authorId;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public SupersprintEntity(String name, LocalDateTime startAt, LocalDateTime endAt, String authorId) {
        this.name = name;
        this.startAt = startAt;
        this.endAt = endAt;
        this.authorId = authorId;
    }
    public SupersprintEntity(String name, LocalDateTime startAt, LocalDateTime endAt, String authorId, LocalDateTime createdAt) {
        this.name = name;
        this.startAt = startAt;
        this.endAt = endAt;
        this.authorId = authorId;
        this.createdAt = createdAt;
    }
}
