package ewp.tasktracker.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "workloads")
public class WorkloadEntity extends BaseEntity{
    private String id;
    private String name;
    private String status;
    private String author_id;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date created_at;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updated_at;

}
