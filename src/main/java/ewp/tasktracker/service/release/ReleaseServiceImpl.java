package ewp.tasktracker.service.release;

import ewp.tasktracker.api.dto.release.CreateReleaseRq;
import ewp.tasktracker.entity.ReleaseEntity;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.ReleaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ReleaseServiceImpl implements ReleaseService {

    private final ReleaseRepository releaseRepository;

    @Override
    public ReleaseEntity save(CreateReleaseRq dto) {
        return releaseRepository.save(dto.toEntity());
    }

    @Override
    public ReleaseEntity findById(String id) {
        return releaseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Release not found, id: " + id));
    }

    @Override
    public List<ReleaseEntity> findAll() {
        return releaseRepository.findAll();
    }
}
