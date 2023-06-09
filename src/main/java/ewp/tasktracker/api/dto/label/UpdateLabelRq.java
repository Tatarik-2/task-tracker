package ewp.tasktracker.api.dto.label;

import ewp.tasktracker.entity.LabelsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateLabelRq {
    @NotNull
    @Length(min = 36, max = 36)
    private String id;
    @NotNull
    @Size(max = 64)
    private String text;
    @Size(max = 32)
    private String taskId;
    @NotNull
    @Size(min = 5, max = 36)
    private String authorId;


    public LabelsEntity toEntity(LabelsEntity labelsEntityFromDB) {
        LabelsEntity labelsEntity = new LabelsEntity();
        labelsEntity.setId(id);
        labelsEntity.setTaskId(taskId);
        labelsEntity.setAuthorId(authorId);
        labelsEntity.setText(text);
        labelsEntity.setCreatedAt(labelsEntityFromDB.getCreatedAt());
        labelsEntity.setUpdatedAt(LocalDateTime.now());
        return labelsEntity;
    }
}
