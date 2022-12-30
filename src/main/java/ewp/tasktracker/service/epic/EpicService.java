package ewp.tasktracker.service.epic;

import ewp.tasktracker.api.dto.epic.CreateEpicRq;
import ewp.tasktracker.api.dto.epic.EpicDto;
import ewp.tasktracker.api.dto.epic.UpdateEpicRq;
import ewp.tasktracker.api.dto.page.PageDto;

import java.util.List;

public interface EpicService {

    EpicDto save(CreateEpicRq createEpicRq);

    EpicDto update(UpdateEpicRq updateEpicRq);

    List<EpicDto> getListOfEpics(Integer pageSize, Integer pageNumber);

    EpicDto findById(String id);

    PageDto<EpicDto> findByName(String name, Integer pageNumber, Integer pageSize);
}
