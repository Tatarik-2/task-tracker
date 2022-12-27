package ewp.tasktracker.entity;

import ewp.tasktracker.entity.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "deliveries")
public class DeliveryEntity extends BaseEntity {
    private String name;
    private String version;

    @ManyToOne
    @JoinColumn(name = "release_id", nullable = false)
    private ReleaseEntity release;
}
