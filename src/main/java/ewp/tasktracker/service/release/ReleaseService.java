package ewp.tasktracker.service.release;

import ewp.tasktracker.api.dto.release.CreateReleaseRq;
import ewp.tasktracker.entity.ReleaseEntity;

import java.util.List;

/**
 * Сервис для работы с релизами
 */
public interface ReleaseService {

    ReleaseEntity save(CreateReleaseRq dto);

    ReleaseEntity findById(String  id);

    List<ReleaseEntity> findAll();
}
