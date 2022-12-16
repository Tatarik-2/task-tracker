package ewp.tasktracker.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Collections;
import java.util.List;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "releases")
public class ReleaseEntity extends BaseEntity {
    private String name;
    @OneToMany(mappedBy = "release", fetch = FetchType.EAGER)
    private List<DeliveryEntity> deliveries = Collections.emptyList();

    public ReleaseEntity(String name) {
        this.name = name;
    }
}
