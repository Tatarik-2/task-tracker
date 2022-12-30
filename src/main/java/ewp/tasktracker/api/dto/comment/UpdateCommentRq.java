package ewp.tasktracker.api.dto.comment;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Valid
public class UpdateCommentRq {
    @NotBlank
    private String id;
    @NotBlank
    @Size(min = 2, max = 128)
    private String text;
    @NotBlank
    private String taskId;
}
