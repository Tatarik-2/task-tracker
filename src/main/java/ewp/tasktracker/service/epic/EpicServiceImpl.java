package ewp.tasktracker.service.epic;

import ewp.tasktracker.api.dto.epic.CreateEpicRq;
import ewp.tasktracker.api.dto.epic.EpicDto;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.EpicRepository;
import ewp.tasktracker.service.epic.EpicService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EpicServiceImpl implements EpicService {

    private final EpicRepository epicRepository;

    @Override
    public EpicDto saveEpic(CreateEpicRq createEpicRq) {
        return new EpicDto(epicRepository.save(createEpicRq.toEntity()));
    }

//    Реализовать
//    @Override
//    public EpicEntity update(String id, EpicDto epicDto) {
//        return null;
//    }

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
