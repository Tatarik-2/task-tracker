package ewp.tasktracker.api.controller;

import ewp.tasktracker.api.dto.task.CreateTaskRq;
import ewp.tasktracker.api.dto.task.TaskDto;
import ewp.tasktracker.api.dto.task.UpdateTaskRq;
import ewp.tasktracker.service.task.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/task",
        produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Api(value = "task-tracker", tags = {"tasks"})
public class TaskController {

    private final TaskService tasksService;

    @GetMapping
    @ApiOperation(value = "Получить список задач", response = TaskDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<List<TaskDto>> getAllTask(@RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                    @RequestParam(value = "pageNumber") Integer pageNumber) {
        return ResponseEntity.ok(tasksService.findAllTask(pageSize, pageNumber));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Получить задачу по id", response = TaskDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 404, message = "Задача не найдена"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<TaskDto> getById(@PathVariable String id) {
        TaskDto taskDto = tasksService.findById(id);
        return ResponseEntity.ok(taskDto);
    }

    @PostMapping
    @ApiOperation(value = "Сохранить задачу", response = TaskDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 422, message = "Unprocessable Entity - ошибка валидации"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<TaskDto> create(@Valid @RequestBody CreateTaskRq dto) {
        TaskDto taskDto = tasksService.create(dto);
        return ResponseEntity.ok(taskDto);
    }

    @PutMapping
    @ApiOperation(value = "Обновление задач", response = TaskDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 404, message = "Сущность не найдена"),
            @ApiResponse(code = 422, message = "Unprocessable Entity - ошибка валидации"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<TaskDto> updateTask(@Validated @RequestBody UpdateTaskRq dto) {
        return ResponseEntity.ok(tasksService.updateTask(dto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Удалить задачу", response = TaskDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное удаление задачи"),
            @ApiResponse(code = 500, message = "Внутрення ошибка сервера"),
            @ApiResponse(code = 404, message = "Задача не найдена")
    })
    public ResponseEntity<TaskDto> deleteTask(@PathVariable String id) {
        TaskDto response = tasksService.deleteById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
