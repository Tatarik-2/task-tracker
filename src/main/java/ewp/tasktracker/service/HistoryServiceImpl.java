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
        return new HistoryDto(historyRepository.save(dto.toEntity()));
    }

    @Override
    public HistoryDto findHistoryById(String id) {
        return new HistoryDto(historyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("History not found, id: " + id)));

    }

    @Override
    public List<HistoryDto> findAllHistories() {
        return historyRepository.findAll().stream().map(HistoryDto::new).collect(Collectors.toList());
    }

    @Override
    public HistoryDto updateHistory(HistoryDto dto) {
        HistoryEntity historyEntityOld = historyRepository.findById(dto.getId()).orElseThrow(() ->
                new ResourceNotFoundException("History not found, id: " + dto.getId()));
        HistoryEntity historyEntityNew = new HistoryEntity();
        historyEntityNew.setId(historyEntityOld.getId());
        historyEntityNew.setCreatedAt(historyEntityOld.getCreatedAt());
        historyEntityNew.setName(dto.getName());
        historyEntityNew.setDescription(dto.getDescription());
        historyEntityNew.setStatus(dto.getStatus().toString());
        historyEntityNew.setPriority(dto.getPriority().toString());
        historyEntityNew.setEpicId(dto.getEpicId());
        historyEntityNew.setAuthorId(dto.getAuthorId());
        historyEntityNew.setSprintId(dto.getSprintId());
        return new HistoryDto(historyRepository.save(historyEntityNew));
    }
}
