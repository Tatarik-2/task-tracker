package ewp.tasktracker.service.task;

import ewp.tasktracker.api.dto.bug.BugDto;
import ewp.tasktracker.api.dto.page.PageDto;
import ewp.tasktracker.api.dto.task.CreateTaskRq;
import ewp.tasktracker.api.dto.task.TaskDto;
import ewp.tasktracker.api.dto.task.UpdateTaskRq;
import ewp.tasktracker.api.util.PageUtil;
import ewp.tasktracker.entity.TaskEntity;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final PageUtil pageUtil;

    @Override
    public TaskDto create(CreateTaskRq dto) {
        TaskEntity entity = taskRepository.save(dto.toEntity());
        return new TaskDto(entity);
    }

    @Override
    public TaskDto saveTask(CreateTaskRq dto) {
        TaskEntity taskEntity = taskRepository.save(dto.toEntity());
        return new TaskDto(taskEntity);
    }

    @Override
    public TaskDto findById(String id) {
        return new TaskDto(taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found, id: " + id)));
    }


    @Override
    public PageDto<TaskDto> findTaskByName(String name, Integer pageSize, Integer pageNumber) {
        pageSize = pageUtil.pageSizeControl(pageSize);
        Page<TaskEntity> taskEntityPage = taskRepository.findByName(name.toUpperCase(),
                Pageable.ofSize(pageSize).withPage(pageNumber));
        if (taskEntityPage.getContent().isEmpty()) {
            return PageDto.getEmptyPageDto();
        }
        List<TaskDto> taskDtoList = taskEntityPage.stream().map(TaskDto::new).collect(Collectors.toList());
        return new PageDto<>(taskDtoList, pageNumber, pageSize, taskDtoList.size());
    }

    @Override
    public PageDto<TaskDto> findTaskByAssigneeId(String assigneeId, Integer pageSize, Integer pageNumber) {
        pageSize = pageUtil.pageSizeControl(pageSize);
        Page<TaskEntity> pageOfTaskEntity = taskRepository.findByAssigneeIdStartingWith(assigneeId,
                Pageable.ofSize(pageSize).withPage(pageNumber));
        if (pageOfTaskEntity.getContent().isEmpty()) {
            return PageDto.getEmptyPageDto();
        }
        List<TaskDto> listOfTaskDto = pageOfTaskEntity.stream().map(TaskDto::new).collect(Collectors.toList());
        PageDto<TaskDto> newPageDto = new PageDto<>(listOfTaskDto, pageNumber, pageSize, listOfTaskDto.size());
        return newPageDto;
    }

    @Override
    public TaskDto deleteById(String id) {
        TaskDto task = findById(id);
        taskRepository.deleteById(id);
        return task;
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
