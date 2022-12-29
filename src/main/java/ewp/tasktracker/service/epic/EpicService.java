package ewp.tasktracker.service.epic;

import ewp.tasktracker.api.dto.epic.CreateEpicRq;
import ewp.tasktracker.api.dto.epic.EpicDto;

public interface EpicService {

    EpicDto saveEpic(CreateEpicRq createEpicRq);

//    EpicEntity update(String id, EpicDto epicDto);

//    List<EpicDto> findAllEpics();

    EpicDto findEpicById(String id);
}
