package ewp.tasktracker.repository;

import ewp.tasktracker.entity.BugEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BugRepository extends JpaRepository<BugEntity, String> {
}
