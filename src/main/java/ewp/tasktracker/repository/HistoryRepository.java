package ewp.tasktracker.repository;

import ewp.tasktracker.entity.HistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<HistoryEntity, String> {
    @Query("SELECT hr FROM histories hr WHERE UPPER(hr.name) LIKE %?1%")
    Page<HistoryEntity> findByName(String filterForName, Pageable pageable);
}
