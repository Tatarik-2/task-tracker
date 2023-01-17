package ewp.tasktracker.api.dto.label;

import ewp.tasktracker.entity.LabelsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
        return new LabelsEntity(this.taskId, this.authorId, this.text);
    }
}
