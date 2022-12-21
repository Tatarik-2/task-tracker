package ewp.tasktracker.service;

import ewp.tasktracker.api.dto.CreateWorkloadRq;
import ewp.tasktracker.api.dto.WorkloadDto;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.WorkloadRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class WorkloadServiceImpl implements WorkloadService {

    private final WorkloadRepository workloadRepository;

    @Override
    public WorkloadDto create(CreateWorkloadRq dto) {
        return new WorkloadDto(workloadRepository.save(dto.toEntity()));
    }

    @Override
    public WorkloadDto findById(String id) {
        return new WorkloadDto(workloadRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Workload not found, id: " + id)));
    }

    @Override
    public List<WorkloadDto> findAll() {
        return workloadRepository.findAll().stream().map(WorkloadDto::new).collect(Collectors.toList());
    }
}
