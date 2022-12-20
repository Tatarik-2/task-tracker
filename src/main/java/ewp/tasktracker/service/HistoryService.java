package ewp.tasktracker.service;

import ewp.tasktracker.api.dto.CreateHistoryRq;
import ewp.tasktracker.entity.HistoryEntity;

import java.util.List;

public interface HistoryService {
    HistoryEntity saveHistory(CreateHistoryRq dto);

    HistoryEntity findHistoryById(String id);

    List<HistoryEntity> findAllHistories();

}
