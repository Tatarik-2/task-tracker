package ewp.tasktracker.service.task;

import ewp.tasktracker.api.dto.task.CreateTaskRq;
import ewp.tasktracker.api.dto.task.TaskDto;
import ewp.tasktracker.api.dto.task.UpdateTaskRq;

import java.util.List;

public interface TaskService {

    TaskDto create(CreateTaskRq dto);

    TaskDto findById(String id);

    TaskDto deleteById(String id);

    TaskDto updateTask(UpdateTaskRq dto);

    List<TaskDto> findAllTask(Integer pageSize, Integer pageNumber);
}

