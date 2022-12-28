package ewp.tasktracker.entity.common;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.UUID;

@NoArgsConstructor
@Setter
@MappedSuperclass
public class BaseEntity implements Persistable<String> {
    @Id
    protected String id = UUID.randomUUID().toString();
    @Transient
    private Boolean justCreated = false;

    public BaseEntity(String id) {
        this.id = id;
        this.justCreated = true;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return justCreated;
    }
}
