package ewp.tasktracker.service;

import ewp.tasktracker.api.dto.CreateWorkloadRq;
import ewp.tasktracker.entity.WorkloadEntity;

import java.util.List;

/**
 * Сервис для работы с поставками
 */
public interface WorkloadService {

    WorkloadEntity save(CreateWorkloadRq dto);

    WorkloadEntity findById(String id);

    List<WorkloadEntity> findAll();
}
