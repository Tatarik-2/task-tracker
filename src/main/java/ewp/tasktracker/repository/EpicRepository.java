package ewp.tasktracker.repository;


import ewp.tasktracker.entity.EpicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import java.util.List;

public interface EpicRepository extends JpaRepository<EpicEntity, String> {

    List<EpicEntity> findByName(String name);

}
