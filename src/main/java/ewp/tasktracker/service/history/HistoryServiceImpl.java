package ewp.tasktracker.service.history;

import ewp.tasktracker.api.dto.history.CreateHistoryRq;
import ewp.tasktracker.api.dto.history.HistoryDto;
import ewp.tasktracker.api.dto.history.UpdateHistoryRq;
import ewp.tasktracker.api.dto.page.PageDto;
import ewp.tasktracker.api.util.PageUtil;
import ewp.tasktracker.entity.HistoryEntity;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.HistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;
    private final PageUtil pageUtil;

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
    public List<HistoryDto> findAllHistories(Integer pageSize, Integer pageNumber) {
        pageSize = pageUtil.pageSizeControl(pageSize);
        return historyRepository.findAll(PageRequest.of(pageNumber, pageSize))
                .stream().map(HistoryDto::new).collect(Collectors.toList());
    }

    @Override
    public HistoryDto updateHistory(UpdateHistoryRq dto) {
        HistoryEntity historyEntityOld = historyRepository.findById(dto.getId()).orElseThrow(() ->
                new ResourceNotFoundException("History not found, id: " + dto.getId()));
        HistoryEntity historyEntityNew = dto.updateEntity(historyEntityOld);
        HistoryEntity resultEntity = historyRepository.save(historyEntityNew);
        return new HistoryDto(resultEntity);
    }

    @Override
    public PageDto<HistoryDto> findHistoryByName(String name, Integer pageSize, Integer pageNumber) {
        pageSize = pageUtil.pageSizeControl(pageSize);
        Page<HistoryEntity> historyEntityPage = historyRepository.findByName(name.toUpperCase(),
                Pageable.ofSize(pageSize).withPage(pageNumber));
        if (historyEntityPage.getContent().isEmpty()) {
            return PageDto.getEmptyPageDto();
        }
        List<HistoryDto> historyDtoList = historyEntityPage.getContent()
                .stream().map(HistoryDto::new).collect(Collectors.toList());
        return new PageDto<>(historyDtoList, pageNumber, pageSize, historyDtoList.size());
    }
}
