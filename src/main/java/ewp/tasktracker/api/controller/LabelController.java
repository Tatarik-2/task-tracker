package ewp.tasktracker.api.controller;

import ewp.tasktracker.api.dto.LabelsDto;
import ewp.tasktracker.entity.LabelsEntity;
import ewp.tasktracker.service.LabelsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/label",
        produces = {MediaType.APPLICATION_JSON_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Api(value = "task-tracker", tags = {"labels"})
public class LabelController {
    private final LabelsService labelService;

    @GetMapping
    @ApiOperation(value = "Получить список меток", response = LabelsDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<List<LabelsDto>> getAll() {
        List<LabelsDto> labels= labelService.findAll().stream().map(LabelsDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(labels);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Получить метку по id", response = LabelsDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<LabelsDto> getById(@PathVariable String id){
        LabelsEntity labelsEntity = labelService.findById(id);
        return ResponseEntity.ok(new LabelsDto(labelsEntity));
    }

}
