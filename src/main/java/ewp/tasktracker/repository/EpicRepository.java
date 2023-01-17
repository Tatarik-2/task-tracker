package ewp.tasktracker.repository;

import ewp.tasktracker.entity.EpicEntity;
import ewp.tasktracker.entity.HistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EpicRepository extends JpaRepository<EpicEntity, String> {

    @Query("SELECT ep FROM epics ep WHERE UPPER(ep.name) LIKE %?1%")
    Page<EpicEntity> findByName(String filterForName, Pageable pageable);
    List<EpicEntity> findByName(String name);

}
