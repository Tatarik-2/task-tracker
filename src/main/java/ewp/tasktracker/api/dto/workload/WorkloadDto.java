package ewp.tasktracker.api.dto.workload;

import ewp.tasktracker.entity.common.Status;
import ewp.tasktracker.entity.WorkloadEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkloadDto {
    private String name;
    private Status status;
    private String authorId;

    public WorkloadDto(WorkloadEntity workloadEntity) {
        this.name = workloadEntity.getName();
        this.status = workloadEntity.getStatus();
        this.authorId = workloadEntity.getAuthorId();
    }
}
