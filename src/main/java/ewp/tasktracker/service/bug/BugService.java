package ewp.tasktracker.service.bug;

import ewp.tasktracker.api.dto.bug.BugDto;
import ewp.tasktracker.api.dto.bug.CreateBugRq;
import ewp.tasktracker.api.dto.bug.UpdateBugRq;
import ewp.tasktracker.api.dto.page.PageDto;

import java.util.List;

public interface BugService {
    BugDto create(CreateBugRq dto);

    BugDto findById(String id);

    PageDto<BugDto> findByName(String name, Integer pageSize, Integer pageNumber);

    List<BugDto> findByAssigneeId(String assigneeId);

    List<BugDto> findAll(Integer pageSize, Integer pageNumber);

    BugDto update(UpdateBugRq dto);

    BugDto delete(String id);
}
