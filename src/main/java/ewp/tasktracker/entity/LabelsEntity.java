package ewp.tasktracker.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "labels")
public class LabelsEntity extends BaseEntity {
    private String text;
    private String author_id;
    private String task_id;
    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime created_at;
    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime updated_at;
}
