package ewp.tasktracker.service;

import ewp.tasktracker.api.dto.CreateWorkloadRq;
import ewp.tasktracker.api.dto.UpdateWorkloadRq;
import ewp.tasktracker.api.dto.WorkloadDto;
import ewp.tasktracker.api.util.PageUtil;
import ewp.tasktracker.entity.WorkloadEntity;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.WorkloadRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class WorkloadServiceImpl implements WorkloadService {

    private final WorkloadRepository workloadRepository;
    private final PageUtil pageUtil;

    @Override
    public WorkloadDto create(CreateWorkloadRq dto) {
        WorkloadEntity createdWorkloadDto = workloadRepository.save(dto.toEntity());
        return new WorkloadDto(createdWorkloadDto);
    }

    @Override
    public WorkloadDto findById(String id) {
        WorkloadEntity workloadEntity = workloadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Workload not found, id: " + id));
        return new WorkloadDto(workloadEntity);
    }

    @Override
    public List<WorkloadDto> findAll(Integer pageSize, Integer pageNumber) {
        Integer size = pageUtil.pageSizeControl(pageSize);
        return workloadRepository.findAll(PageRequest.of(pageNumber, size))
                .stream().map(WorkloadDto::new).collect(Collectors.toList());
    }

    @Override
    public WorkloadDto update(UpdateWorkloadRq dto) {
        WorkloadEntity workloadEntityFromDB = workloadRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Workload not found, id: " + dto.getId()));
        WorkloadEntity workloadEntityForUpdate = dto.toEntity(workloadEntityFromDB);
        workloadEntityForUpdate.setId(dto.getId());
        return new WorkloadDto(workloadRepository.save(workloadEntityForUpdate));
    }
}
