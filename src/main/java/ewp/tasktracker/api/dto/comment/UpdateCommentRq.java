package ewp.tasktracker.api.dto.comment;

import ewp.tasktracker.entity.CommentEntity;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Valid
@Data
public class UpdateCommentRq {
    @NotNull(message = "Id не должно быть null")
    @NotBlank(message = "Id не должно быть пустым")
    private String id;
    @NotNull(message = "Текст комментария не может быть пустым")
    @NotBlank
    @Size(min = 2, max = 128, message = "Длина комментария от 2 до 128 символов")
    private String text;
    @NotBlank
    @NotNull(message = "taskId не может быть пустым")
    private String taskId;

    public CommentEntity toEntity() {
        CommentEntity entity = new CommentEntity(this.text, this.taskId);
        entity.setId(this.id);
        entity.setUpdatedAd(LocalDateTime.now());
        return entity;
    }
}
