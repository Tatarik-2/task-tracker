package ewp.tasktracker.api.dto;

import ewp.tasktracker.entity.LabelsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLabelRq {
    @NotEmpty
    @Size(max = 64)
    private String text;
    @NotEmpty
    @Size(max = 36)
    private String author_id;

    @NotEmpty
    @Size(max = 36)
    private String task_id;

    public LabelsEntity toEntity() {
        return new LabelsEntity(
        this.text,
        this.author_id,
        this.task_id
        );
    }
}
