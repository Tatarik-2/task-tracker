package ewp.tasktracker.repository;

import ewp.tasktracker.api.dto.EpicDto;
import ewp.tasktracker.entity.EpicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpicRepository extends JpaRepository<EpicEntity, String> {

}
