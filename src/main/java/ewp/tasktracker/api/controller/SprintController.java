package ewp.tasktracker.api.controller;


import ewp.tasktracker.api.dto.sprint.CreateSprintRq;
import ewp.tasktracker.api.dto.sprint.SprintDto;
import ewp.tasktracker.service.sprint.SprintService;
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
@RequestMapping(value = "/api/sprint",
        produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Api(value = "task-tracker", tags = {"sprint"})
public class SprintController {
    private final SprintService sprintService;
    @GetMapping
    @ApiOperation(value = "Получить список спринтов", response = SprintDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<List<SprintDto>> getAll(@RequestParam (value = "pageSize", required = false) Integer pageSize,
                                                  @RequestParam("pageNumber") Integer pageNumber) {
        return ResponseEntity.ok(sprintService.findAll(pageSize, pageNumber));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Получить метку по id", response = SprintDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 404, message = "Сущность не найдена"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<SprintDto> getById(@PathVariable String id){
        return ResponseEntity.ok(sprintService.findById(id));
    }

    @PostMapping
    @ApiOperation(value = "Сохранить спринт", response = SprintDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 422, message = "Unprocessable Entity - ошибка в валидации полей сущности"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<SprintDto> save(@Valid @RequestBody CreateSprintRq dto){
        return ResponseEntity.ok(sprintService.save(dto));
    }

    public ResponseEntity<SprintDto> delete(@PathVariable String id){
        SprintDto returnDto = sprintService.findById(id);
        sprintService.delete(id);
        return ResponseEntity.ok(returnDto);
    }
}
