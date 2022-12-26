package ewp.tasktracker.service;

import ewp.tasktracker.api.dto.BugDto;
import ewp.tasktracker.api.dto.CreateBugRq;
import ewp.tasktracker.api.dto.UpdateBugRq;

import java.util.List;

public interface BugService {
    BugDto create(CreateBugRq dto);

    BugDto findById(String id);

    List<BugDto> findAll();

    BugDto update(UpdateBugRq dto);
}
