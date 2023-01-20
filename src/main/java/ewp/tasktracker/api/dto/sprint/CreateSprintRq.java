package ewp.tasktracker.api.dto.sprint;

import ewp.tasktracker.entity.SprintEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSprintRq {
    @NotNull
    @Size(min = 3, max = 128)
    private String name;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    @NotNull
    @Size(min = 5, max = 36)
    private String authorId;
    @NotNull
    @Size(min = 5, max = 36)
    private String superSprintId;


    public SprintEntity toEntity() {
        return new SprintEntity(
                this.name,
                this.startAt,
                this.endAt,
                this.authorId,
                this.superSprintId
        );
    }
}
