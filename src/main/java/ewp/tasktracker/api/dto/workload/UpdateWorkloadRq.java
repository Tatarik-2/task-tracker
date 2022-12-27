package ewp.tasktracker.api.dto.workload;

import ewp.tasktracker.entity.common.Status;
import ewp.tasktracker.entity.WorkloadEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateWorkloadRq {
    @NotNull
    @Length(min = 36, max = 36)
    private String id;
    @NotNull
    @Size(min = 3, max = 128)
    private String name;
    @Enumerated(EnumType.STRING)
    private Status status;
    @NotNull
    @Size(min = 5, max = 36)
    private String authorId;


    public WorkloadEntity toEntity(WorkloadEntity workloadEntityFromDB) {
        return new WorkloadEntity(
                this.name,
                this.status,
                this.authorId,
                workloadEntityFromDB.getCreatedAt(),
                LocalDateTime.now()
        );
    }
}
