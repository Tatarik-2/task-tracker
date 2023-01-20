package ewp.tasktracker.repository;

import ewp.tasktracker.entity.EpicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EpicRepository extends JpaRepository<EpicEntity, String> {

    @Query("SELECT ep FROM epics ep WHERE UPPER(ep.name) LIKE %?1%")
    Page<EpicEntity> findByName(String filterForName, Pageable pageable);


}
