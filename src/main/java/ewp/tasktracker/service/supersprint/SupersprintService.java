package ewp.tasktracker.service.supersprint;

import ewp.tasktracker.api.dto.supersprint.CreateSupersprintRq;
import ewp.tasktracker.api.dto.supersprint.SupersprintDto;
import ewp.tasktracker.api.dto.supersprint.UpdateSupersprintRq;

import java.util.List;

/**
 * Сервис для работы с суперспринтами
 */
public interface SupersprintService {

    SupersprintDto create(CreateSupersprintRq dto);

    SupersprintDto findById(String id);

    List<SupersprintDto> findAll(Integer pageSize, Integer pageNumber);

    SupersprintDto update(UpdateSupersprintRq dto);
}
