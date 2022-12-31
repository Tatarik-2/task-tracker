package ewp.tasktracker.repository;

import ewp.tasktracker.entity.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<HistoryEntity, String> {
    List<HistoryEntity> findByName(String name);
}
