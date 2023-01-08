package ewp.tasktracker.entity;

import ewp.tasktracker.entity.common.Status;
import lombok.*;
import ewp.tasktracker.entity.common.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "workloads")
public class WorkloadEntity extends BaseEntity{

    private String name;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String authorId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Override
    public String toString() {
        return "WorkloadEntity{" +
                "name='" + name + '\'' +
                ", status=" + status +
                ", authorId='" + authorId + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", id='" + id + '\'' +
                '}';
    }
}
