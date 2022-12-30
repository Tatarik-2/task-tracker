package ewp.tasktracker.repository;

import ewp.tasktracker.entity.EpicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EpicRepository extends JpaRepository<EpicEntity, String> {

//    @Query(value = "select e from epics e WHERE e.name = ?1")
//    Page<EpicEntity> findEpicEntityByName(String name, Pageable pageable);

}
