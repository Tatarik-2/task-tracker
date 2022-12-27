package ewp.tasktracker.service.epic;

import ewp.tasktracker.api.dto.epic.CreateEpicRq;
import ewp.tasktracker.api.dto.epic.EpicDto;
import ewp.tasktracker.api.dto.epic.UpdateEpicRq;

import java.util.List;

public interface EpicService {

    EpicDto saveEpic(CreateEpicRq createEpicRq);

    EpicDto updateEpic(UpdateEpicRq updateEpicRq);

//    List<EpicDto> findAllEpics(Integer pageSize, Integer pageNumber);

    EpicDto findEpicById(String id);
}
