package ewp.tasktracker.service;

import ewp.tasktracker.api.dto.CreateTaskRq;
import ewp.tasktracker.api.dto.TaskDto;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.TaskRepository;

import java.util.List;
import java.util.stream.Collectors;


public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskDto create(CreateTaskRq dto) {

        return new TaskDto(taskRepository.save(dto.toEntity()));
    }

    @Override
    public TaskDto findById(String id) {
        return new TaskDto(taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Workload not found, id: " + id)));
    }

    @Override
    public List<TaskDto> findAll() {
        return taskRepository.findAll().stream().map(TaskDto::new).collect(Collectors.toList());
    }
}
