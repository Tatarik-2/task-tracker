package ewp.tasktracker.service;

import ewp.tasktracker.api.dto.CreateDeliveryRq;
import ewp.tasktracker.api.dto.CreateLabelRq;
import ewp.tasktracker.entity.LabelsEntity;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.LabelsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class LabelsServiceImpl implements LabelsService {
    private final LabelsRepository labelsRepository;


    @Override
    public LabelsEntity save(CreateLabelRq dto) {
        return labelsRepository.save(dto.toEntity());
    }

    @Override
    public LabelsEntity findById(String id) {
        return labelsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Release not found, id: " + id));
    }

    @Override
    public List<LabelsEntity> findAll() {
        return labelsRepository.findAll();
    }
}
