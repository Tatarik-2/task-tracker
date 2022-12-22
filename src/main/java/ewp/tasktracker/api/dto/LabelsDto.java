package ewp.tasktracker.api.dto;

import ewp.tasktracker.entity.LabelsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabelsDto {
    private String id;
    private String text;
    private String author_id;

    private String task_id;
    private Date created_at;
    private Date updated_at;

    public LabelsDto(LabelsEntity labelsEntity) {
        this.id = labelsEntity.getId();
        this.text = labelsEntity.getText();
        this.author_id = labelsEntity.getAuthor_id();
    }
}
