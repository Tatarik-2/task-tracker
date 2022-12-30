package ewp.tasktracker.service.epic;

import ewp.tasktracker.api.dto.epic.CreateEpicRq;
import ewp.tasktracker.api.dto.epic.EpicDto;
import ewp.tasktracker.api.dto.epic.UpdateEpicRq;
import ewp.tasktracker.api.dto.page.PageDto;
import ewp.tasktracker.api.util.PageUtil;
import ewp.tasktracker.entity.EpicEntity;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.EpicRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class EpicServiceImpl implements EpicService {

    private final EpicRepository epicRepository;
    private final PageUtil pageUtil;

    @Override
    public EpicDto save(CreateEpicRq createEpicRq) {
        EpicEntity epicEntity = epicRepository.save(createEpicRq.toEntity());
        return new EpicDto(epicEntity);
    }

    @Override
    public EpicDto update(UpdateEpicRq updateEpicRq) {
        EpicEntity epicToUpdate = updateEpicRq.toEntity(getEpicEntityFromRepo(updateEpicRq.getId()), updateEpicRq);
        EpicEntity updatedEpic = epicRepository.save(epicToUpdate);
        return new EpicDto(updatedEpic);
    }


    @Override
    public List<EpicDto> getListOfEpics(Integer pageSize, Integer pageNumber) {
        Integer checkedPageSize = pageUtil.pageSizeControl(pageSize);
        List<EpicDto> listOfEpics = epicRepository.findAll(PageRequest.of(pageNumber, checkedPageSize))
                .stream().map(EpicDto::new).collect(Collectors.toList());
        return listOfEpics;
    }

    @Override
    public EpicDto findById(String id) {
        return new EpicDto(getEpicEntityFromRepo(id));
    }

    private EpicEntity getEpicEntityFromRepo(String id) {
        return epicRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Epic not found, id: " + id));
    }


    public PageDto<EpicDto> findByName(String name, Integer pageNumber, Integer pageSize) {
        List<EpicEntity> pageOfEpics = epicRepository.findByName(name);
        Integer checkedPageSize = pageUtil.pageSizeControl(pageSize);
        if (pageOfEpics.isEmpty()) {
            return PageDto.getEmptyPageDto();
        }
        List<EpicDto> epicsDto = pageOfEpics.stream().map(EpicDto::new).collect(Collectors.toList());
        PageDto<EpicDto> pageDto = new PageDto<>(epicsDto, pageNumber, checkedPageSize, epicsDto.size());
        return pageDto;
    }
}
