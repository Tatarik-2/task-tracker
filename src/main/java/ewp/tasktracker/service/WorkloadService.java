package ewp.tasktracker.service;

import ewp.tasktracker.api.dto.CreateWorkloadRq;
import ewp.tasktracker.api.dto.UpdateWorkloadRq;
import ewp.tasktracker.api.dto.WorkloadDto;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с рабочими пространствами
 */
public interface WorkloadService {

    WorkloadDto create(CreateWorkloadRq dto);

    WorkloadDto findById(String id);

    List<WorkloadDto> findAll(Optional<Integer> pageSize, Integer pageNumber);

    WorkloadDto update(UpdateWorkloadRq dto);
}
