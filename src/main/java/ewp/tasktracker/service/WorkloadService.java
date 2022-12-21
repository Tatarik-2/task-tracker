package ewp.tasktracker.service;

import ewp.tasktracker.api.dto.CreateWorkloadRq;
import ewp.tasktracker.api.dto.WorkloadDto;
import ewp.tasktracker.entity.WorkloadEntity;

import java.util.List;

/**
 * Сервис для работы с рабочими пространствами
 */
public interface WorkloadService {

    WorkloadDto create(CreateWorkloadRq dto);

    WorkloadDto findById(String id);

    List<WorkloadDto> findAll();
}
