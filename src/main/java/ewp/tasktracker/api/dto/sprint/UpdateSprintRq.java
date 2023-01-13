package ewp.tasktracker.api.dto.sprint;

import ewp.tasktracker.entity.SprintEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSprintRq {
    @NotNull
    @Length(min = 36, max = 36)
    private String id;
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


    public SprintEntity toEntity(SprintEntity sprintEntityFromDb) {
        return new SprintEntity(
                this.name,
                this.startAt,
                this.endAt,
                this.authorId,
                this.superSprintId
        );
    }
}
