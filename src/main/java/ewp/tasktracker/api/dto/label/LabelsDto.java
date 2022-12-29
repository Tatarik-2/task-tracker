package ewp.tasktracker.api.dto.label;

import ewp.tasktracker.entity.LabelsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabelsDto {
    private String id;
    private String text;
    private String authorId;

    private String taskId;

    public LabelsDto(LabelsEntity labelsEntity) {
        this.id = labelsEntity.getId();
        this.text = labelsEntity.getText();
        this.authorId = labelsEntity.getAuthorId();
        this.taskId = labelsEntity.getTaskId();
    }
}
