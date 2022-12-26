package ewp.tasktracker.service;

import ewp.tasktracker.api.dto.CreateTaskRq;
import ewp.tasktracker.api.dto.TaskDto;

import java.util.List;

public interface TaskService {

    TaskDto create(CreateTaskRq dto);

    TaskDto findById(String id);

    List<TaskDto> findAll();

}

