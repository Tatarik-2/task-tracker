package ewp.tasktracker.api.dto;

import ewp.tasktracker.entity.ReleaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReleaseDto {
    private String id;
    private String name;
    private List<DeliveryDto> deliveries;

    public ReleaseDto(ReleaseEntity releaseEntity) {
        this.id = releaseEntity.getId();
        this.name = releaseEntity.getName();
        this.deliveries = releaseEntity.getDeliveries().stream().map(DeliveryDto::new).collect(Collectors.toList());
    }

    public ReleaseEntity toEntity() {
        return new ReleaseEntity(
                this.name
        );
    }
}
