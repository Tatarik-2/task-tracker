package ewp.tasktracker.api.controller;

import ewp.tasktracker.api.dto.BugDto;
import ewp.tasktracker.api.dto.CreateBugRq;
import ewp.tasktracker.service.BugService;
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
@RequestMapping(value = "/api/bug",
        produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Api(value = "task-tracker", tags = {"bug"})
public class BugController {
    private final BugService bugService;

    @GetMapping
    @ApiOperation(value = "Получить список багов", response = BugDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<List<BugDto>> getAll() {
        List<BugDto> bugs = bugService.findAll();
        return ResponseEntity.ok(bugs);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Получить баг по id", response = BugDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 404, message = "Сущность не найдена"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<BugDto> getById(@PathVariable String id) {
        BugDto bugDto = bugService.findById(id);
        return ResponseEntity.ok(bugDto);
    }

    @PostMapping
    @ApiOperation(value = "Создать баг", response = BugDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса"),
            @ApiResponse(code = 422, message = "Ошибка валидации")
    })
    public ResponseEntity<BugDto> create(@Valid @RequestBody CreateBugRq dto) {
        BugDto bugDto = bugService.create(dto);
        return ResponseEntity.ok(bugDto);
    }
}

