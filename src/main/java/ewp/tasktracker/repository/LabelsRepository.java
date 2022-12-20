package ewp.tasktracker.repository;

import ewp.tasktracker.entity.LabelsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelsRepository extends JpaRepository<LabelsEntity, String> {
}
