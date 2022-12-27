package ewp.tasktracker.service.labels;

import ewp.tasktracker.api.dto.label.CreateLabelRq;
import ewp.tasktracker.api.dto.label.LabelsDto;

import java.util.List;

public interface LabelsService {
    LabelsDto save(CreateLabelRq dto);

    LabelsDto findById(String id);
    List<LabelsDto> findAll();
}
