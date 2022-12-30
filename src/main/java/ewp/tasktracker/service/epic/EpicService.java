package ewp.tasktracker.service.epic;

import ewp.tasktracker.api.dto.epic.CreateEpicRq;
import ewp.tasktracker.api.dto.epic.EpicDto;
import ewp.tasktracker.api.dto.epic.UpdateEpicRq;
import ewp.tasktracker.api.dto.page.PageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EpicService {

    EpicDto saveEpic(CreateEpicRq createEpicRq);

    EpicDto updateEpic(UpdateEpicRq updateEpicRq);

    List<EpicDto> getListOfEpics(Integer pageSize, Integer pageNumber);

    EpicDto findEpicById(String id);

//    PageDto<EpicDto> findEpicByName(String name, Integer pageNumber, Integer pageSize);
}
