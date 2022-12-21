package ewp.tasktracker.api.dto;

import ewp.tasktracker.Status;
import ewp.tasktracker.entity.DeliveryEntity;
import ewp.tasktracker.entity.ReleaseEntity;
import ewp.tasktracker.entity.WorkloadEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateWorkloadRq {
    @NotNull
    @Size(min = 3, max = 128)
    private String name;
    private Status status;
    @NotNull
    @Size(min = 5, max = 36)
    private String author_id;


    public WorkloadEntity toEntity() {
        return new WorkloadEntity(
                this.name,
                this.status.toString(),
                this.author_id,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
