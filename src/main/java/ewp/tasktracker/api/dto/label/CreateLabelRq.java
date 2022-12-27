package ewp.tasktracker.api.dto.label;

import ewp.tasktracker.entity.LabelsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLabelRq {
    @NotEmpty
    @Size(max = 64)
    private String text;
    @NotEmpty
    @Size(max = 36)
    private String authorId;

    @NotEmpty
    @Size(max = 36)
    private String taskId;

    public LabelsEntity toEntity() {
        return new LabelsEntity(this.text = text, this.authorId = authorId, this.taskId = taskId, LocalDateTime.now(), LocalDateTime.now());
    }
}
