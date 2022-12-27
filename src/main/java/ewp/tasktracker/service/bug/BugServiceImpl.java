package ewp.tasktracker.service.bug;

import ewp.tasktracker.api.dto.bug.BugDto;
import ewp.tasktracker.api.dto.bug.CreateBugRq;
import ewp.tasktracker.entity.BugEntity;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.BugRepository;
import ewp.tasktracker.service.bug.BugService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BugServiceImpl implements BugService {
    private final BugRepository bugRepository;

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
    public List<BugDto> findAll() {
        return bugRepository.findAll().stream().
                map(BugDto::new).collect(Collectors.toList());
    }
}

