package ewp.tasktracker.service.labels;


import ewp.tasktracker.api.dto.label.CreateLabelRq;
import ewp.tasktracker.api.dto.label.LabelsDto;
import ewp.tasktracker.api.dto.label.UpdateLabelRq;
import ewp.tasktracker.entity.LabelsEntity;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.LabelsRepository;
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
        return new LabelsDto(labelsRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Label not found, id: " + id)));
    }

    @Override
    public List<LabelsDto> findAll() {
        return labelsRepository.findAll().stream().map(LabelsDto::new).collect(Collectors.toList());
    }

    @Override
    public LabelsDto delete(String id) {
        LabelsDto returnDto = new LabelsDto(labelsRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Label not found, id: " + id)));
        labelsRepository.deleteById(id);
        return returnDto;
    }

    @Override
    public LabelsDto update(UpdateLabelRq dto) {
        LabelsEntity labelsEntityFromDB = labelsRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Label not found, id: " + dto.getId()));
        LabelsEntity labelsEntityForUpdate = dto.toEntity(labelsEntityFromDB);
        labelsEntityForUpdate.setId(dto.getId());
        return new LabelsDto(labelsRepository.save(labelsEntityForUpdate));
    }
}
