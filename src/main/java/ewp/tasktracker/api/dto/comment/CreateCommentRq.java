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
    @NotNull
    @NotBlank
    @Size(min = 2, max = 128)
    private String text;
    @NotNull
    @NotBlank
    private String taskId;

    public CommentEntity toEntity() {
        CommentEntity entity = new CommentEntity(this.text, this.taskId);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAd(LocalDateTime.now());
        return entity;
    }

    @Override
    public String toString() {
        return "{" +
                "\"text\":\"" + text + '\"' +
                ", \"taskId\":\"" + taskId + '\"' +
                '}';
    }
}
