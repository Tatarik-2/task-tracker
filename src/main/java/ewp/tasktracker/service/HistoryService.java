package ewp.tasktracker.service;

import ewp.tasktracker.api.dto.CreateHistoryRq;
import ewp.tasktracker.api.dto.HistoryDto;
import ewp.tasktracker.api.dto.UpdateHistoryRq;

import java.util.List;

public interface HistoryService {
    HistoryDto saveHistory(CreateHistoryRq dto);

    HistoryDto findHistoryById(String id);

    List<HistoryDto> findAllHistories();

    HistoryDto updateHistory(UpdateHistoryRq dto);

}
