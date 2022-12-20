package ewp.tasktracker.service;

import ewp.tasktracker.api.dto.CreateHistoryRq;
import ewp.tasktracker.entity.HistoryEntity;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.HistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;

    @Override
    public HistoryEntity saveHistory(CreateHistoryRq dto) {
        return historyRepository.save(dto.toEntity());
    }

    @Override
    public HistoryEntity findHistoryById(String id) {
        return historyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Release not found, id: " + id));
    }

    @Override
    public List<HistoryEntity> findAllHistories() {
        return historyRepository.findAll();
    }
}
