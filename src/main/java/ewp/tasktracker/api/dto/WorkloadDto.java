package ewp.tasktracker.api.dto;

import ewp.tasktracker.entity.DeliveryEntity;
import ewp.tasktracker.entity.WorkloadEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkloadDto {
    private String id;
    private String name;
    private String status;
    private String author_id;
    private Date created_at;
    private Date updated_at;

    public WorkloadDto(WorkloadEntity workloadEntity) {
        this.id = workloadEntity.getId();
        this.name = workloadEntity.getName();
        this.status = workloadEntity.getStatus();
        this.author_id = workloadEntity.getAuthor_id();
        this.created_at = workloadEntity.getCreated_at();
        this.updated_at = workloadEntity.getUpdated_at();
    }
}
