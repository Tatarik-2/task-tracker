package ewp.tasktracker.service.history;

import ewp.tasktracker.api.dto.history.CreateHistoryRq;
import ewp.tasktracker.api.dto.history.HistoryDto;
import ewp.tasktracker.api.dto.history.UpdateHistoryRq;
import ewp.tasktracker.api.dto.page.PageDto;

import java.util.List;

public interface HistoryService {
    HistoryDto saveHistory(CreateHistoryRq dto);

    HistoryDto findHistoryById(String id);

    List<HistoryDto> findAllHistories(Integer pageSize, Integer pageNumber);

    HistoryDto updateHistory(UpdateHistoryRq dto);

    PageDto<HistoryDto> findHistoryByName(String name, Integer pageSize, Integer pageNumber);

}
