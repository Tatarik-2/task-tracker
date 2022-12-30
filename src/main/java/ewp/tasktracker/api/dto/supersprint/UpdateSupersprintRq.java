package ewp.tasktracker.api.dto.supersprint;

import ewp.tasktracker.entity.SupersprintEntity;
import ewp.tasktracker.entity.WorkloadEntity;
import ewp.tasktracker.entity.common.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSupersprintRq {
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


    public SupersprintEntity toEntity(SupersprintEntity supersprintEntityFromDB) {
        return new SupersprintEntity(
                this.name,
                this.startAt,
                this.endAt,
                this.authorId,
                supersprintEntityFromDB.getCreatedAt()
        );
    }
}
