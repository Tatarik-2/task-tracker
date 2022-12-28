package ewp.tasktracker.api.dto.supersprint;

import ewp.tasktracker.entity.SupersprintEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSupersprintRq {
    @NotNull
    @Size(min = 3, max = 128)
    private String name;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    @NotNull
    @Size(min = 5, max = 36)
    private String authorId;


    public SupersprintEntity toEntity() {
        return new SupersprintEntity(
                this.name,
                this.startAt,
                this.endAt,
                this.authorId
        );
    }
}
