package ewp.tasktracker.api.dto;

import ewp.tasktracker.entity.LabelsEntity;

import java.time.LocalDateTime;
import java.util.Date;

public class CreateLabelRq {
    private String text;
    private String author_id;

    private String task_id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public LabelsEntity toEntity() {
        return new LabelsEntity(
        this.text,
        this.author_id,
        this.task_id,
        this.created_at,
        this.updated_at
        );
    }
}
