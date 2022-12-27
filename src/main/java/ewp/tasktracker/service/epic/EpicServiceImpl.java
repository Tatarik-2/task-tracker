package ewp.tasktracker.service.epic;

import ewp.tasktracker.api.dto.epic.CreateEpicRq;
import ewp.tasktracker.api.dto.epic.EpicDto;
import ewp.tasktracker.api.dto.epic.UpdateEpicRq;
import ewp.tasktracker.entity.EpicEntity;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.EpicRepository;
import ewp.tasktracker.service.epic.EpicService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EpicServiceImpl implements EpicService {

    private final EpicRepository epicRepository;

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


//    @Override
//    public List<EpicDto> findAllEpics(Integer pageSize, Integer pageNumber) {
//        return epicRepository.findAll(PageRequest.of(pageSize, pageNumber)).stream().map(EpicDto::new).collect(Collectors.toList());
//    }

    @Override
    public EpicDto findEpicById(String id) {
        return new EpicDto(epicRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Epic not found, id: " + id)));
    }
}
