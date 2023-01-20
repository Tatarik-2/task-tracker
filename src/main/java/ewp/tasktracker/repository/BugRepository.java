package ewp.tasktracker.repository;

import ewp.tasktracker.entity.BugEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@Repository
public interface BugRepository extends JpaRepository<BugEntity, String> {
    @Query("SELECT b FROM bugs b WHERE UPPER(b.name) LIKE %?1%")
    Page<BugEntity> findByName(String name, Pageable pageable);

    @Query("SELECT b FROM bugs b JOIN histories h on b.historyId = h.id " +
            "JOIN epics e ON h.epicId = e.id WHERE e.projectId = ?1 AND b.createdAt > ?2")
    Page<BugEntity> findByProjectId(String projectId, LocalDateTime date, Pageable pageable);

    @Query("SELECT b FROM bugs b WHERE UPPER(b.assigneeId) LIKE %?1%")
    Page<BugEntity> findByAssigneeId(String assigneeId, Pageable pageable);

}
