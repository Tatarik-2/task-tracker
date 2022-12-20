package ewp.tasktracker.api.dto;

import ewp.tasktracker.entity.LabelsEntity;

import java.util.Date;

public class CreateLabelRq {
    private String id;
    private String text;
    private String author_id;

    private String task_id;
    private Date created_at;
    private Date updated_at;

    public LabelsEntity toEntity() {
        return new LabelsEntity(
                this.id,
        this.text,
        this.author_id,
        this.task_id,
        this.created_at,
        this.updated_at
        );
    }
}
