package ewp.tasktracker.api.dto.comment;

import ewp.tasktracker.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class CommentDto {
    @NotNull
    @NotBlank
    private String id;
    @NotNull
    @NotBlank
    private String text;
    @NotNull
    @NotBlank
    private String taskId;

    public CommentDto(CommentEntity entity) {
        this.id = entity.getId();
        this.text = entity.getText();
        this.taskId = entity.getTaskId();
    }
}
