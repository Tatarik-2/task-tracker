package ewp.tasktracker.entity;

import ewp.tasktracker.entity.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "supersprints")
public class SupersprintEntity extends BaseEntity{

    private String name;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String authorId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
