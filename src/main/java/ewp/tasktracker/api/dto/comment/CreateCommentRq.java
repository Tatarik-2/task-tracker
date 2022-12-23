package ewp.tasktracker.api.dto.comment;

import ewp.tasktracker.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class CreateCommentRq {
    @NotNull(message = "Текст комментаряи не может быть пустым")
    @Size(min = 2, max = 128, message = "Длина комментария от 2 до 128 симовлов")
    private String text;
    @NotNull(message = "taskId не должен быть пустым")
    @NotBlank(message = "")
    private String taskId;
    public CommentEntity toEntity() {
        return new CommentEntity(this.text, this.taskId, LocalDateTime.now(), LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "{" +
                "\"text\":\"" + text + '\"' +
                ", \"taskId\":\"" + taskId + '\"' +
                '}';
    }
}
