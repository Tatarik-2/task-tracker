package ewp.tasktracker.api.dto.comment;

import lombok.Data;

@Data
public class UpdateCommentRq {
    private String id;
    private String text;
    private String taskId;
}
