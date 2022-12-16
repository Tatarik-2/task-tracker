package ewp.tasktracker.api.dto;

import ewp.tasktracker.entity.DeliveryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDto {
    private String id;
    private String name;
    private String version;
    private String releaseId;

    public DeliveryDto(DeliveryEntity deliveryEntity) {
        this.id = deliveryEntity.getId();
        this.name = deliveryEntity.getName();
        this.version = deliveryEntity.getVersion();
        this.releaseId = deliveryEntity.getRelease().getId();
    }
}
