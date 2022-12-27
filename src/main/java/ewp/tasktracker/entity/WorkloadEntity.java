package ewp.tasktracker.entity;

import ewp.tasktracker.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ewp.tasktracker.entity.common.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
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

}
