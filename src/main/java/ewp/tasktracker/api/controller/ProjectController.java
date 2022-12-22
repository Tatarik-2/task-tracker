package ewp.tasktracker.api.controller;

import ewp.tasktracker.api.dto.CreateProjectRq;
import ewp.tasktracker.api.dto.ProjectDto;
import ewp.tasktracker.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/project",
        produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Api(value = "task-tracker", tags = {"project"})
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    @ApiOperation(value = "Получить список проектов", response = ProjectDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<List<ProjectDto>> getAll() {
        List<ProjectDto> projects = projectService.findAll();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Получить проект по id", response = ProjectDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 404, message = "Сущность не найдена"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<ProjectDto> getById(@PathVariable String id) {
        ProjectDto projectDto = projectService.findById(id);
        return ResponseEntity.ok(projectDto);
    }

    @PostMapping
    @ApiOperation(value = "Сохранить проект", response = ProjectDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<ProjectDto> create(@RequestBody CreateProjectRq dto) {
        ProjectDto projectDto = projectService.create(dto);
        return ResponseEntity.ok(projectDto);
    }
}
