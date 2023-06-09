package ewp.tasktracker.api.controller;

import ewp.tasktracker.api.dto.label.CreateLabelRq;
import ewp.tasktracker.api.dto.label.LabelsDto;
import ewp.tasktracker.api.dto.label.UpdateLabelRq;
import ewp.tasktracker.service.labels.LabelsService;
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
@RequestMapping(value = "/api/label",
        produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Api(value = "task-tracker", tags = {"labels"})
@Validated
public class LabelController {
    private final LabelsService labelService;

    @GetMapping
    @ApiOperation(value = "Получить список меток", response = LabelsDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<List<LabelsDto>> getAll(@RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                  @RequestParam(value = "pageNumber") Integer pageNumber) {
        return ResponseEntity.ok(labelService.findAll(pageSize, pageNumber));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Получить метку по id", response = LabelsDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 404, message = "Сущность не найдена"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<LabelsDto> getById(@PathVariable String id){
        return ResponseEntity.ok(labelService.findById(id));
    }

    @PostMapping
    @ApiOperation(value = "Сохранить метку", response = LabelsDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 422, message = "Unprocessable Entity - ошибка в валидации полей сущности"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<LabelsDto> save(@Valid @RequestBody CreateLabelRq dto){
        return ResponseEntity.ok( labelService.save(dto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Удалить метку", response = LabelsDto.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 404, message = "Сущность не найдена"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<LabelsDto> delete(@PathVariable String id){
        LabelsDto dto = labelService.delete(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping
    @ApiOperation(value = "Редактировать метку", response = LabelsDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса"),
            @ApiResponse(code = 422, message = "Ошибка валидации"),
            @ApiResponse(code = 404, message = "Сущность для обновления не найдена")
    })
    public ResponseEntity<LabelsDto> update(@Valid @RequestBody UpdateLabelRq dto) {
        LabelsDto labelsDto = labelService.update(dto);
        return ResponseEntity.ok(labelsDto);
    }

}
