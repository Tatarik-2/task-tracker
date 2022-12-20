package ewp.tasktracker.service;

import ewp.tasktracker.api.dto.CreateWorkloadRq;
import ewp.tasktracker.entity.WorkloadEntity;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.WorkloadRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class WorkloadServiceImpl implements WorkloadService {

    private final WorkloadRepository workloadRepository;

    @Override
    public WorkloadEntity save(CreateWorkloadRq dto) {
        return workloadRepository.save(dto.toEntity());
    }

    @Override
    public WorkloadEntity findById(String id) {
        return workloadRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Release not found, id: " + id));
    }

    @Override
    public List<WorkloadEntity> findAll() {
        return workloadRepository.findAll();
    }
}
