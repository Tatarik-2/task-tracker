package ewp.tasktracker.service;

import ewp.tasktracker.api.dto.CreateHistoryRq;
import ewp.tasktracker.api.dto.HistoryDto;
import ewp.tasktracker.entity.HistoryEntity;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.HistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;

    @Override
    public HistoryDto saveHistory(CreateHistoryRq dto) {
        HistoryEntity historyEntity = historyRepository.save(dto.toEntity());
        return new HistoryDto(historyEntity);
    }

    @Override
    public HistoryDto findHistoryById(String id) {
        HistoryEntity historyEntity = historyRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("History not found, id: " + id));
        return new HistoryDto(historyEntity);

    }

    @Override
    public List<HistoryDto> findAllHistories() {
        return historyRepository.findAll().stream().map(HistoryDto::new).collect(Collectors.toList());
    }
}
