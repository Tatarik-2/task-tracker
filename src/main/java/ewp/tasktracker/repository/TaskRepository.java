package ewp.tasktracker.repository;

import ewp.tasktracker.entity.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, String> {
    @Query("SELECT ts FROM tasks ts WHERE UPPER(ts.name) LIKE %?1%")
    Page<TaskEntity> findByName(String filterForName, Pageable pageable);

    @Query("SELECT t FROM tasks t JOIN histories h on t.historyId = h.id " +
            "JOIN epics e ON h.epicId = e.id WHERE e.projectId = ?1 AND t.createdAt > ?2")
    Page<TaskEntity> findByProjectId(String projectId, LocalDateTime date, Pageable pageable);

    Page<TaskEntity> findByAssigneeIdStartingWith(String filterForAssigneeId, Pageable pageable);


}
