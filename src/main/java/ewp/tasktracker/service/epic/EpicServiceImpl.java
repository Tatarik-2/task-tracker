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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class EpicServiceImpl implements EpicService {

    private final EpicRepository epicRepository;
    private final PageUtil pageUtil;

    @Override
    public EpicDto saveEpic(CreateEpicRq createEpicRq) {
        EpicEntity epicEntity = epicRepository.save(createEpicRq.toEntity());
        return new EpicDto(epicEntity);
    }

    @Override
    public EpicDto updateEpic(UpdateEpicRq updateEpicRq) {
        EpicEntity epicFromDb = epicRepository.findById(updateEpicRq.getId()).orElseThrow(() ->
                new ResourceNotFoundException("Epic not found, id: " + updateEpicRq.getId()));
        EpicEntity epicToUpdate = updateEpicRq.toEntity(epicFromDb, updateEpicRq);
        EpicEntity updatedEpic = epicRepository.save(epicToUpdate);
        return new EpicDto(updatedEpic);
    }


    @Override
    public List<EpicDto> getListOfEpics(Integer pageSize, Integer pageNumber) {
        pageSize = pageUtil.pageSizeControl(pageSize);
        List<EpicDto> listOfEpics = epicRepository.findAll(PageRequest.of(pageNumber, pageSize))
                .stream().map(EpicDto::new).collect(Collectors.toList());
        return listOfEpics;
    }

    @Override
    public EpicDto findEpicById(String id) {
        return new EpicDto(epicRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Epic not found, id: " + id)));
    }


//    public PageDto<EpicDto> findEpicByName(String name, Integer pageNumber, Integer pageSize) {
//        pageSize = pageUtil.pageSizeControl(pageSize);
//        Pageable pageable = PageRequest.of(pageNumber, pageSize);
//        Page<EpicEntity> pageOfEpics = epicRepository.findEpicEntityByName(name, pageable);
//        if (pageOfEpics.isEmpty()) {
//            throw new ResourceNotFoundException("Epic not found, name: " + name);
//        } else {
//            List<EpicDto> epicsDto = pageOfEpics.stream().map(EpicDto::new).collect(Collectors.toList());
//            PageDto<EpicDto> pageDto = new PageDto<>(epicsDto, pageNumber, pageSize, epicsDto.size());
//            return pageDto;
//        }
//    }


}
