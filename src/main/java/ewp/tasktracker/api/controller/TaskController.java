package ewp.tasktracker.api.controller;

import ewp.tasktracker.api.dto.CreateTaskRq;
import ewp.tasktracker.api.dto.TaskDto;
import ewp.tasktracker.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<TaskDto>> getAll() {
        List<TaskDto> tasks = tasksService.findAll();
        return ResponseEntity.ok(tasks);
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

}
