package ewp.tasktracker.service.labels;

import ewp.tasktracker.api.dto.label.CreateLabelRq;
import ewp.tasktracker.api.dto.label.LabelsDto;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.LabelsRepository;
import ewp.tasktracker.service.labels.LabelsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class LabelsServiceImpl implements LabelsService {
    private final LabelsRepository labelsRepository;


    @Override
    public LabelsDto save(CreateLabelRq dto) {
        return new LabelsDto(labelsRepository.save(dto.toEntity()));
    }

    @Override
    public LabelsDto findById(String id) {
        return new LabelsDto(labelsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Label not found, id: " + id)));
    }

    @Override
    public List<LabelsDto> findAll() {
        return labelsRepository.findAll().stream().map(LabelsDto::new).collect(Collectors.toList());
    }
}
