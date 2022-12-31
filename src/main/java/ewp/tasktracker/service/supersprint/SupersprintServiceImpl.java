package ewp.tasktracker.service.supersprint;

import ewp.tasktracker.api.dto.supersprint.CreateSupersprintRq;
import ewp.tasktracker.api.dto.supersprint.SupersprintDto;
import ewp.tasktracker.api.dto.supersprint.UpdateSupersprintRq;
import ewp.tasktracker.api.util.PageUtil;
import ewp.tasktracker.entity.SupersprintEntity;
import ewp.tasktracker.exception.LocalDateTimeIncorrectException;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.SupersprintRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SupersprintServiceImpl implements SupersprintService {

    private final SupersprintRepository supersprintRepository;
    private final PageUtil pageUtil;

    @Override
    public SupersprintDto create(CreateSupersprintRq dto) {
        if (dto.getStartAt().isAfter(dto.getEndAt())){
            throw new LocalDateTimeIncorrectException("The start must be before the end");
        }
        SupersprintEntity createdSupersprintDto = supersprintRepository.save(dto.toEntity());
        return new SupersprintDto(createdSupersprintDto);
    }

    @Override
    public SupersprintDto findById(String id) {
        SupersprintEntity supersprintEntity = supersprintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supersprint not found, id: " + id));
        return new SupersprintDto(supersprintEntity);
    }

    @Override
    public List<SupersprintDto> findAll(Integer pageSize, Integer pageNumber) {
        Integer size = pageUtil.pageSizeControl(pageSize);
        return supersprintRepository.findAll(PageRequest.of(pageNumber, size))
                .stream().map(SupersprintDto::new).collect(Collectors.toList());
    }

    @Override
    public SupersprintDto update(UpdateSupersprintRq dto) {
        if (dto.getStartAt().isAfter(dto.getEndAt())){
            throw new LocalDateTimeIncorrectException("The start must be before the end");
        }
        SupersprintEntity supersprintEntityFromDB = supersprintRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Supersprint not found, id: " + dto.getId()));
        SupersprintEntity supersprintEntityForUpdate = dto.toEntity(supersprintEntityFromDB);
        supersprintEntityForUpdate.setId(dto.getId());
        return new SupersprintDto(supersprintRepository.save(supersprintEntityForUpdate));
    }
}
