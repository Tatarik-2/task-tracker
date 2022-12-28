package ewp.tasktracker.service.sprint;

import ewp.tasktracker.api.dto.label.CreateLabelRq;
import ewp.tasktracker.api.dto.sprint.SprintDto;

import java.util.List;

public interface SprintService {
    SprintDto save(CreateLabelRq dto);

    SprintDto findById(String id);
    List<SprintDto> findAll();
}
