package ewp.tasktracker.service.bug;

import ewp.tasktracker.api.dto.bug.BugDto;
import ewp.tasktracker.api.dto.bug.CreateBugRq;
import ewp.tasktracker.api.dto.bug.UpdateBugRq;
import ewp.tasktracker.api.dto.page.PageDto;
import ewp.tasktracker.api.util.PageUtil;
import ewp.tasktracker.entity.BugEntity;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.BugRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BugServiceImpl implements BugService {
    private final BugRepository bugRepository;
    private final PageUtil pageUtil;

    @Override
    public BugDto create(CreateBugRq dto) {
        BugEntity entity = bugRepository.save(dto.toEntity());
        return new BugDto(entity);
    }

    @Override
    public BugDto findById(String id) {
        return new BugDto(bugRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Bug not found, id: " + id)));
    }

    @Override
    public PageDto<BugDto> findByName(String name, Integer pageSize, Integer pageNumber) {
        pageSize = pageUtil.pageSizeControl(pageSize);
        Page<BugEntity> bugEntityPage = bugRepository.findByName(name.toUpperCase(),
                Pageable.ofSize(pageSize).withPage(pageNumber));
        if (bugEntityPage.isEmpty()) {
            return PageDto.getEmptyPageDto();
        }
        List<BugDto> bugDtoList = bugEntityPage.stream().map(BugDto::new).collect(Collectors.toList());
        return new PageDto<>(bugDtoList, pageNumber, pageSize, bugDtoList.size());
    }

    @Override
    public PageDto<BugDto> findByProjectId(String projectId, LocalDateTime dateTime, Integer pageSize, Integer pageNumber) {
        pageSize = pageUtil.pageSizeControl(pageSize);
        Page<BugEntity> bugEntityPage = bugRepository.findByProjectId(projectId, dateTime,
                Pageable.ofSize(pageSize).withPage(pageNumber));
        if (bugEntityPage.isEmpty()) {
            return PageDto.getEmptyPageDto();
        }
        List<BugDto> bugDtoList = bugEntityPage.stream().map(BugDto::new).collect(Collectors.toList());
        return new PageDto<>(bugDtoList, pageNumber, pageSize, bugDtoList.size());
    }

    @Override
    public List<BugDto> findAll(Integer pageSize, Integer pageNumber) {
        pageSize = pageUtil.pageSizeControl(pageSize);
        return bugRepository.findAll(PageRequest.of(pageNumber, pageSize)).stream().
                map(BugDto::new).collect(Collectors.toList());
    }

    @Override
    public BugDto update(UpdateBugRq dto) {
        BugEntity bugEntity = bugRepository.findById(dto.getId()).orElseThrow(() ->
                new ResourceNotFoundException("Bug not found, id: " + dto.getId()));
        BugEntity bugEntityNew = dto.updateEntity(bugEntity);
        BugEntity resultEntity = bugRepository.save(bugEntityNew);
        return new BugDto(resultEntity);
    }

    @Override
    public BugDto delete(String id) {
        BugDto bugDto = findById(id);
        bugRepository.deleteById(id);
        return bugDto;
    }
}

