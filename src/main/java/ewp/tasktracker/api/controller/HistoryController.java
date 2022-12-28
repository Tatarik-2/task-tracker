package ewp.tasktracker.api.controller;

import ewp.tasktracker.api.dto.history.CreateHistoryRq;
import ewp.tasktracker.api.dto.history.HistoryDto;
import ewp.tasktracker.api.dto.history.UpdateHistoryRq;
import ewp.tasktracker.api.dto.page.PageDto;
import ewp.tasktracker.service.history.HistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/history",
        produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Api(value = "task-tracker", tags = {"history"})
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping
    @ApiOperation(value = "Получить список историй", response = HistoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })

    public ResponseEntity<List<HistoryDto>> getAllHistories(@RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                            @RequestParam(value = "pageNumber") Integer pageNumber) {
        return ResponseEntity.ok(historyService.findAllHistories(pageSize, pageNumber));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Получить историю по id", response = HistoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 404, message = "Сущность не найдена"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<HistoryDto> getHistoryById(@PathVariable String id) {
        return ResponseEntity.ok(historyService.findHistoryById(id));
    }

    @PostMapping
    @ApiOperation(value = "Сохранить историю", response = HistoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 422, message = "Unprocessable Entity - ошибка в валидации полей сущности"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<HistoryDto> createHistory(@Validated @RequestBody CreateHistoryRq dto) {
        return ResponseEntity.ok(historyService.saveHistory(dto));
    }

    @PutMapping
    @ApiOperation(value = "Обновить историю", response = HistoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 404, message = "Сущность не найдена"),
            @ApiResponse(code = 422, message = "Unprocessable Entity - ошибка в валидации полей сущности"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<HistoryDto> updateHistory(@Validated @RequestBody UpdateHistoryRq dto) {
        return ResponseEntity.ok(historyService.updateHistory(dto));
    }

    @GetMapping("search")
    @ApiOperation(value = "Поиск истории по названию", response = PageDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 404, message = "Сущность не найдена"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<PageDto<HistoryDto>> getHistoryById(@RequestParam(value = "filter") String filter,
                                                              @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                              @RequestParam(value = "pageNumber") Integer pageNumber) {
        return ResponseEntity.ok(historyService.findHistoryByName(filter, pageSize, pageNumber));
    }
}
