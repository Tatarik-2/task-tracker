package ewp.tasktracker.repository;

import ewp.tasktracker.entity.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, String> {
    @Query("SELECT ts FROM tasks ts WHERE UPPER(ts.name) LIKE %?1%")
    Page<TaskEntity> findByName(String filterForName, Pageable pageable);
    @Query("SELECT ts FROM tasks ts WHERE UPPER(ts.assigneeId) LIKE UPPER(CONCAT(?1, '%'))")
    List<TaskEntity> findByAssigneeId(String filterForAssigneeId);


}
