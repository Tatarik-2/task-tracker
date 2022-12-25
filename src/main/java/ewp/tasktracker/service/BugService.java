package ewp.tasktracker.service;

import ewp.tasktracker.api.dto.BugDto;
import ewp.tasktracker.api.dto.CreateBugRq;

import java.util.List;

public interface BugService {
    BugDto create(CreateBugRq dto);

    BugDto findById(String id);

    List<BugDto> findAll();
}
