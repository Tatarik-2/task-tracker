package ewp.tasktracker.service.labels;

import ewp.tasktracker.api.dto.label.CreateLabelRq;
import ewp.tasktracker.api.dto.label.LabelsDto;
import ewp.tasktracker.api.dto.label.UpdateLabelRq;

import java.util.List;

public interface LabelsService {
    LabelsDto save(CreateLabelRq dto);

    LabelsDto findById(String id);
    List<LabelsDto> findAll(Integer pageSize, Integer pageNumber);
    LabelsDto delete(String id);

    LabelsDto update(UpdateLabelRq dto);
}
