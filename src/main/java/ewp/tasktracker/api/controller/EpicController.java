package ewp.tasktracker.api.controller;

import ewp.tasktracker.api.dto.*;
import ewp.tasktracker.service.EpicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/epic",
        produces = {MediaType.APPLICATION_JSON_VALUE}
//        consumes = {MediaType.APPLICATION_JSON_VALUE}
)

@AllArgsConstructor
@Api(value = "task-tracker", tags = {"epic"})
public class EpicController {
    private final EpicService epicService;

//    Пагинация
//    @GetMapping
//    @ApiOperation(value = "Получить список эпиков", response = EpicDto.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Успешный ответ"),
//            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
//    })
//    public ResponseEntity<List<EpicDto>> getAll() {
//        return ResponseEntity.ok(epicService.findAll());
//    }


    @GetMapping("/{id}")
    @ApiOperation(value = "Получить эпик по id", response = EpicDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 404, message = "Сущность не найдена"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<EpicDto> getEpicById(@PathVariable String id) {
        return ResponseEntity.ok(epicService.findEpicById(id));
    }


    @PostMapping
    @ApiOperation(value = "Сохранить эпик", response = EpicDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<EpicDto> saveEpic(@Valid @RequestBody CreateEpicRq dto) {
        return ResponseEntity.ok(epicService.saveEpic(dto));
    }
}
