package ewp.tasktracker.service.bug;

import ewp.tasktracker.api.dto.bug.BugDto;
import ewp.tasktracker.api.dto.bug.CreateBugRq;
import ewp.tasktracker.api.dto.bug.UpdateBugRq;

import java.util.List;

public interface BugService {
    BugDto create(CreateBugRq dto);

    BugDto findById(String id);

    List<BugDto> findAll();

    BugDto update(UpdateBugRq dto);
}
