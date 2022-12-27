package ewp.tasktracker.service;

import ewp.tasktracker.api.dto.CreateTaskRq;
import ewp.tasktracker.api.dto.TaskDto;
import ewp.tasktracker.api.dto.UpdateTaskRq;
import ewp.tasktracker.entity.TaskEntity;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
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
    public List<TaskDto> findAll() {
        return taskRepository.findAll().stream().map(TaskDto::new).collect(Collectors.toList());
    }

    @Override
    public TaskDto updateTask(UpdateTaskRq dto) {
        TaskEntity taskEntityOne = taskRepository.findById(dto.getId()).orElseThrow(() ->
                new ResourceNotFoundException("Task not found, id: " + dto.getId()));
        TaskEntity taskEntityNew = dto.updateEntity(taskEntityOne, dto);
        TaskEntity resultEntity = taskRepository.save(taskEntityNew);
        return new TaskDto(resultEntity);
    }
}
