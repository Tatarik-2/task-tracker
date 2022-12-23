package ewp.tasktracker.service;

import ewp.tasktracker.api.dto.CreateEpicRq;
import ewp.tasktracker.api.dto.EpicDto;

public interface EpicService {

    EpicDto saveEpic(CreateEpicRq createEpicRq);

//    EpicEntity update(String id, EpicDto epicDto);

//    List<EpicDto> findAll();

    EpicDto findEpicById(String id);
}
