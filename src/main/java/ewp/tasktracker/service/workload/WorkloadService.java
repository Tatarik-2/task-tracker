package ewp.tasktracker.service.workload;

import ewp.tasktracker.api.dto.workload.CreateWorkloadRq;
import ewp.tasktracker.api.dto.workload.UpdateWorkloadRq;
import ewp.tasktracker.api.dto.workload.WorkloadDto;

import java.util.List;

/**
 * Сервис для работы с рабочими пространствами
 */
public interface WorkloadService {

    WorkloadDto create(CreateWorkloadRq dto);

    WorkloadDto findById(String id);

    List<WorkloadDto> findAll(Integer pageSize, Integer pageNumber);

    WorkloadDto update(UpdateWorkloadRq dto);
}
