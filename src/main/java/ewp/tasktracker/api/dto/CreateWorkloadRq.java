package ewp.tasktracker.api.dto;

import ewp.tasktracker.entity.DeliveryEntity;
import ewp.tasktracker.entity.ReleaseEntity;
import ewp.tasktracker.entity.WorkloadEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateWorkloadRq {
    private String id;
    private String name;
    private String status;
    private String author_id;
    private Date created_at;
    private Date updated_at;

    public WorkloadEntity toEntity() {
        return new WorkloadEntity(
                this.id,
                this.name,
                this.status,
                this.author_id,
                this.created_at,
                this.updated_at
        );
    }
}
