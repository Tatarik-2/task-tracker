package ewp.tasktracker.service;

import ewp.tasktracker.api.dto.epic.CreateEpicRq;
import ewp.tasktracker.api.dto.epic.EpicDto;
import ewp.tasktracker.api.dto.epic.UpdateEpicRq;
import ewp.tasktracker.entity.EpicEntity;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.EpicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
//    public List<EpicDto> findAllEpics() {
//        return epicRepository.findAll(PageRequest.of(1, 2)).stream().map(EpicDto::new).collect(Collectors.toList());
//    }

    @Override
    public EpicDto findEpicById(String id) {
        return new EpicDto(epicRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Epic not found, id: " + id)));
    }
}
