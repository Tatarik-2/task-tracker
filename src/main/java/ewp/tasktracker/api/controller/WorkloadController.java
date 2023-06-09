package ewp.tasktracker.api.controller;

import ewp.tasktracker.api.dto.workload.CreateWorkloadRq;
import ewp.tasktracker.api.dto.workload.UpdateWorkloadRq;
import ewp.tasktracker.api.dto.workload.WorkloadDto;
import ewp.tasktracker.service.workload.WorkloadService;
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
    public ResponseEntity<List<WorkloadDto>> getAll(@RequestParam (value = "pageSize", required = false) Integer pageSize,
                                                    @RequestParam("pageNumber") Integer pageNumber) {
        List<WorkloadDto> workloads = workloadService.findAll(pageSize, pageNumber);
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

    @PutMapping
    @ApiOperation(value = "Редактировать рабочее пространство", response = WorkloadDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса"),
            @ApiResponse(code = 422, message = "Ошибка валидации"),
            @ApiResponse(code = 404, message = "Сущность для обновления не найдена")
    })
    public ResponseEntity<WorkloadDto> update(@Valid @RequestBody UpdateWorkloadRq dto) {
        WorkloadDto workloadDto = workloadService.update(dto);
        return ResponseEntity.ok(workloadDto);
    }
}
