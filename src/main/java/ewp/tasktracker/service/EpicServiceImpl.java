package ewp.tasktracker.service;

import ewp.tasktracker.api.dto.CreateEpicRq;
import ewp.tasktracker.api.dto.EpicDto;
import ewp.tasktracker.entity.EpicEntity;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.EpicRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class EpicServiceImpl implements EpicService{

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
