package ewp.tasktracker.service.sprint;

import ewp.tasktracker.api.dto.sprint.CreateSprintRq;
import ewp.tasktracker.api.dto.sprint.SprintDto;
import ewp.tasktracker.api.dto.sprint.UpdateSprintRq;

import java.util.List;

public interface SprintService {
    SprintDto save(CreateSprintRq dto);

    SprintDto findById(String id);
    List<SprintDto> findAll(Integer pageSize, Integer pageNumber);
    SprintDto delete(String id);
    SprintDto update(UpdateSprintRq dto);
}
