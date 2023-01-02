package ewp.tasktracker.api.controller;

import ewp.tasktracker.api.dto.project.CreateProjectRq;
import ewp.tasktracker.api.dto.project.ProjectDto;
import ewp.tasktracker.api.dto.project.UpdateProjectRq;
import ewp.tasktracker.service.project.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<List<ProjectDto>> getAll(@RequestParam (value = "pageSize", required = false) Integer pageSize,
                                                   @RequestParam("pageNumber") Integer pageNumber) {
        List<ProjectDto> projects = projectService.findAll(pageSize, pageNumber);
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
    public ResponseEntity<ProjectDto> create(@Validated @RequestBody CreateProjectRq dto) {
        ProjectDto projectDto = projectService.create(dto);
        return ResponseEntity.ok(projectDto);
    }

    @PutMapping()
    @ApiOperation(value = "Редактировать проект", response = ProjectDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса"),
            @ApiResponse(code = 422, message = "Ошибка валидации"),
            @ApiResponse(code = 404, message = "Сущность для обновления не найдена")
    })
    public ResponseEntity<ProjectDto> update(@Valid @RequestBody UpdateProjectRq dto) {
        ProjectDto projectDto = projectService.updateProject(dto);
        return ResponseEntity.ok(projectDto);
    }
}
