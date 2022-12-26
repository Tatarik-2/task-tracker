package ewp.tasktracker.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "workloads")
public class WorkloadEntity extends BaseEntity{

    private String name;
    private String status;
    private String authorId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
