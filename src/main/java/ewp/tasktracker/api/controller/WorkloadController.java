package ewp.tasktracker.api.controller;

import ewp.tasktracker.api.dto.CreateWorkloadRq;
import ewp.tasktracker.api.dto.WorkloadDto;
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

@RestController
@RequestMapping(value = "/api/workload",
        produces = {MediaType.APPLICATION_JSON_VALUE}
)
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
        List<WorkloadDto> workloads = workloadService.findAll();
        return ResponseEntity.ok(workloads);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Получить рабочее пространство по id", response = WorkloadDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 404, message = "Сущность не найдена"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<WorkloadDto> getById(@PathVariable String id) {
        WorkloadDto workloadDto = workloadService.findById(id);
        return ResponseEntity.ok(workloadDto);
    }

    @PostMapping
    @ApiOperation(value = "Создать рабочее пространство", response = WorkloadDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса"),
            @ApiResponse(code = 422, message = "Ошибка валидации")
    })
    public ResponseEntity<WorkloadDto> create(@Valid @RequestBody CreateWorkloadRq dto) {
        WorkloadDto workloadDto = workloadService.create(dto);
        return ResponseEntity.ok(workloadDto);
    }
}
