package ewp.tasktracker.service.sprint;

import ewp.tasktracker.api.dto.sprint.CreateSprintRq;
import ewp.tasktracker.api.dto.sprint.SprintDto;
import ewp.tasktracker.api.dto.sprint.UpdateSprintRq;
import ewp.tasktracker.api.util.PageUtil;
import ewp.tasktracker.entity.SprintEntity;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.SprintRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SprintServiceImpl implements SprintService{

    private final  SprintRepository sprintRepository;
    private final PageUtil pageUtil;


    @Override
    public SprintDto save(CreateSprintRq dto) {
        return new SprintDto(sprintRepository.save(dto.toEntity()));
    }

    @Override
    public SprintDto findById(String id) {

        return new SprintDto(sprintRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Sprint not found, id: " + id)));
    }

    @Override
    public List<SprintDto> findAll(Integer pageSize, Integer pageNumber) {
        Integer size = pageUtil.pageSizeControl(pageSize);
        return sprintRepository.findAll(PageRequest.of(pageNumber, size))
                .stream().map(SprintDto::new).collect(Collectors.toList());
    }

    @Override
    public SprintDto delete(String id) {
        SprintDto returnDto =  findById(id);
        sprintRepository.deleteById(id);
        return returnDto;
    }

    @Override
    public SprintDto update(UpdateSprintRq dto) {
        SprintEntity sprintEntityFromDB = sprintRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Sprint not found, id: " + dto.getId()));
        SprintEntity sprintEntityForUpdate = dto.toEntity(sprintEntityFromDB);
        sprintEntityForUpdate.setId(dto.getId());
        return new SprintDto(sprintRepository.save(sprintEntityForUpdate));
    }
}
