package ewp.tasktracker.api.controller;

import ewp.tasktracker.api.dto.supersprint.CreateSupersprintRq;
import ewp.tasktracker.api.dto.supersprint.SupersprintDto;
import ewp.tasktracker.api.dto.supersprint.UpdateSupersprintRq;
import ewp.tasktracker.service.supersprint.SupersprintService;
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
@RequestMapping(value = "/api/supersprint",
        produces = {MediaType.APPLICATION_JSON_VALUE}
)
@AllArgsConstructor
@Api(value = "task-tracker", tags = {"supersprint"})
public class SupersprintController {

    private final SupersprintService supersprintService;

    @GetMapping
    @ApiOperation(value = "Получить список суперспринтов", response = SupersprintDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<List<SupersprintDto>> getAll(@RequestParam (value = "pageSize", required = false) Integer pageSize,
                                                    @RequestParam("pageNumber") Integer pageNumber) {
        List<SupersprintDto> supersprints = supersprintService.findAll(pageSize, pageNumber);
        return ResponseEntity.ok(supersprints);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Получить суперспринт по id", response = SupersprintDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 404, message = "Суперспринт не найден"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<SupersprintDto> getById(@PathVariable String id) {
        SupersprintDto supersprintsDto = supersprintService.findById(id);
        return ResponseEntity.ok(supersprintsDto);
    }

    @PostMapping
    @ApiOperation(value = "Создать суперспринт", response = SupersprintDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса"),
            @ApiResponse(code = 422, message = "Ошибка валидации")
    })
    public ResponseEntity<SupersprintDto> create(@Valid @RequestBody CreateSupersprintRq dto) {
        SupersprintDto supersprintsDto = supersprintService.create(dto);
        return ResponseEntity.ok(supersprintsDto);
    }

    @PutMapping
    @ApiOperation(value = "Редактировать суперспринт", response = SupersprintDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса"),
            @ApiResponse(code = 422, message = "Ошибка валидации"),
            @ApiResponse(code = 404, message = "Суперспринт для обновления не найден")
    })
    public ResponseEntity<SupersprintDto> update(@Valid @RequestBody UpdateSupersprintRq dto) {
        SupersprintDto supersprintsDto = supersprintService.update(dto);
        return ResponseEntity.ok(supersprintsDto);
    }
}
