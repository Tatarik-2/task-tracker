package ewp.tasktracker.api.controller;

import ewp.tasktracker.api.dto.epic.CreateEpicRq;
import ewp.tasktracker.api.dto.epic.EpicDto;
import ewp.tasktracker.api.dto.epic.UpdateEpicRq;
import ewp.tasktracker.api.dto.page.PageDto;
import ewp.tasktracker.service.epic.EpicService;
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
@RequestMapping(value = "/api/epic",
        produces = {MediaType.APPLICATION_JSON_VALUE}
)

@AllArgsConstructor
@Api(value = "task-tracker", tags = {"epic"})
public class EpicController {
    private final EpicService epicService;

    @GetMapping
    @ApiOperation(value = "Получить список эпиков", response = EpicDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<List<EpicDto>> getListOfEpics(@RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                        @RequestParam("pageNumber") Integer pageNumber) {
        List<EpicDto> listOfEpics = epicService.getListOfEpics(pageSize, pageNumber);
        return ResponseEntity.ok(listOfEpics);
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "Получить эпик по id", response = EpicDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 404, message = "Сущность не найдена"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<EpicDto> getEpicById(@PathVariable String id) {
        EpicDto epicDto = epicService.findById(id);
        return ResponseEntity.ok(epicDto);
    }

    @GetMapping("/search")
    @ApiOperation(value = "Поиск эпика по имени", response = EpicDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 404, message = "Сущность не найдена"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<PageDto<EpicDto>> findEpicsByName(@RequestParam(value = "filter") String filter,
                                                            @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                            @RequestParam("pageNumber") Integer pageNumber) {
        PageDto<EpicDto> epicDto = epicService.findByName(filter, pageNumber, pageSize);
        return ResponseEntity.ok(epicDto);
    }


    @PostMapping
    @ApiOperation(value = "Сохранить эпик", response = EpicDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса"),
            @ApiResponse(code = 422, message = "Unprocessable Entity")
    })
    public ResponseEntity<EpicDto> createEpic(@Valid @RequestBody CreateEpicRq dto) {
        return ResponseEntity.ok(epicService.save(dto));
    }

    @PutMapping
    @ApiOperation(value = "Обновить эпик", response = EpicDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса"),
            @ApiResponse(code = 422, message = "Ошибка валидации"),
            @ApiResponse(code = 404, message = "Сущность для обновления не найдена")
    })
    public ResponseEntity<EpicDto> updateEpic(@Valid @RequestBody UpdateEpicRq dto) {
        EpicDto epicDto = epicService.update(dto);
        return ResponseEntity.ok(epicDto);
    }
}
