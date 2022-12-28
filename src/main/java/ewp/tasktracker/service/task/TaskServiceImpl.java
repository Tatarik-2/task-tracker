package ewp.tasktracker.service.task;

import ewp.tasktracker.api.dto.task.CreateTaskRq;
import ewp.tasktracker.api.dto.task.TaskDto;
import ewp.tasktracker.api.dto.task.UpdateTaskRq;
import ewp.tasktracker.api.util.PageUtil;
import ewp.tasktracker.entity.TaskEntity;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.TaskRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final PageUtil pageUtil;

    public TaskServiceImpl(TaskRepository taskRepository, PageUtil pageUtil) {
        this.taskRepository = taskRepository;
        this.pageUtil = pageUtil;
    }

    @Override
    public TaskDto create(CreateTaskRq dto) {
        TaskEntity entity = taskRepository.save(dto.toEntity());
        return new TaskDto(entity);
    }

    @Override
    public TaskDto findById(String id) {
        return new TaskDto(taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found, id: " + id)));
    }

    @Override
    public TaskDto updateTask(UpdateTaskRq dto) {
        TaskEntity taskEntityOne = taskRepository.findById(dto.getId()).orElseThrow(() ->
                new ResourceNotFoundException("Task not found, id: " + dto.getId()));
        TaskEntity taskEntityNew = dto.updateEntity(taskEntityOne, dto);
        TaskEntity resultEntity = taskRepository.save(taskEntityNew);
        return new TaskDto(resultEntity);
    }


    public List<TaskDto> findAllTask(Integer pageSize, Integer pageNumber) {
        pageSize = pageUtil.pageSizeControl(pageSize);
        return taskRepository.findAll(PageRequest.of(pageNumber, pageSize))
                .stream().map(TaskDto::new).collect(Collectors.toList());
    }
}
