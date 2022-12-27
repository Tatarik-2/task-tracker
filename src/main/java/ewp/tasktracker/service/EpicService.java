package ewp.tasktracker.service;

import ewp.tasktracker.api.dto.epic.CreateEpicRq;
import ewp.tasktracker.api.dto.epic.EpicDto;
import ewp.tasktracker.api.dto.epic.UpdateEpicRq;

public interface EpicService {

    EpicDto saveEpic(CreateEpicRq createEpicRq);

    EpicDto updateEpic(UpdateEpicRq updateEpicRq);

//    List<EpicDto> findAllEpics();

    EpicDto findEpicById(String id);
}
