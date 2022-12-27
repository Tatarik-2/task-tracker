package ewp.tasktracker.service;

import ewp.tasktracker.api.dto.CreateEpicRq;
import ewp.tasktracker.api.dto.EpicDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EpicService {

    EpicDto saveEpic(CreateEpicRq createEpicRq);

//    EpicEntity update(String id, EpicDto epicDto);

//    List<EpicDto> findAllEpics();

    EpicDto findEpicById(String id);
}
