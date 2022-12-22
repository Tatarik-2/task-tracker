package ewp.tasktracker.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "labels")
public class LabelsEntity extends BaseEntity {
    private String text;
    private String author_id;
    private String task_id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public LabelsEntity(String text, String author_id, String task_id){
        this.text = text;
        this.author_id = author_id;
        this.task_id = task_id;
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
    }
}
