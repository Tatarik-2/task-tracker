package ewp.tasktracker.api.dto;

import ewp.tasktracker.entity.WorkloadEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkloadDto {
    private String name;
    private String status;
    private String author_id;

    public WorkloadDto(WorkloadEntity workloadEntity) {
        this.name = workloadEntity.getName();
        this.status = workloadEntity.getStatus();
        this.author_id = workloadEntity.getAuthor_id();
    }
}
