package ewp.tasktracker.api.dto.delivery;

import ewp.tasktracker.entity.DeliveryEntity;
import ewp.tasktracker.entity.ReleaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDeliveryRq {
    private String name;
    private String version;
    private String releaseId;

    public DeliveryEntity toEntity(ReleaseEntity releaseEntity) {
        return new DeliveryEntity(
                this.name,
                this.version,
                releaseEntity
        );
    }
}
