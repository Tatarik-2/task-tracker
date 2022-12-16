package ewp.tasktracker.api.dto;

import ewp.tasktracker.entity.ReleaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReleaseRq {
    private String name;

    public ReleaseEntity toEntity() {
        return new ReleaseEntity(
                this.name
        );
    }
}
