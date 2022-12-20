package ewp.tasktracker.api.controller;

import ewp.tasktracker.api.dto.CreateWorkloadRq;
import ewp.tasktracker.api.dto.WorkloadDto;
import ewp.tasktracker.entity.WorkloadEntity;
import ewp.tasktracker.service.WorkloadService;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/workload",
        produces = {MediaType.APPLICATION_JSON_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Api(value = "task-tracker", tags = {"workload"})
public class WorkloadController {

    private final WorkloadService workloadService;

    @GetMapping
    @ApiOperation(value = "Получить список рабочих пространств", response = WorkloadDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<List<WorkloadDto>> getAll() {
        List<WorkloadDto> releases = workloadService.findAll().stream().map(WorkloadDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(releases);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Получить рабочее пространство по id", response = WorkloadDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 404, message = "Сущность не найдена"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<WorkloadDto> getById(@PathVariable String id) {
        WorkloadEntity workloadEntity = workloadService.findById(id);
        return ResponseEntity.ok(new WorkloadDto(workloadEntity));
    }
    /*Поскольку метод помечен аннотацией POST, то и использоваться метод будет для создания рабочего пространства,
    * а не для сохранения, как было по аналогии с остальными*/
    @PostMapping
    @ApiOperation(value = "Создать рабочее пространство", response = WorkloadDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса"),
            @ApiResponse(code = 422, message = "Ошибка валидации")
    })
    public ResponseEntity<WorkloadDto> save(@Valid @RequestBody CreateWorkloadRq dto) {
        WorkloadEntity workloadEntity = workloadService.save(dto);
        return ResponseEntity.ok(new WorkloadDto(workloadEntity));
    }
}
