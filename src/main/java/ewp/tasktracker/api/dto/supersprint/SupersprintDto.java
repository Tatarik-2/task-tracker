package ewp.tasktracker.api.dto.supersprint;

import ewp.tasktracker.entity.SupersprintEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupersprintDto {
    private String name;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String authorId;

    public SupersprintDto(SupersprintEntity supersprintEntity) {
        this.name = supersprintEntity.getName();
        this.startAt = supersprintEntity.getStartAt();
        this.endAt = supersprintEntity.getEndAt();
        this.authorId = supersprintEntity.getAuthorId();
    }
}
