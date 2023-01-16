package ewp.tasktracker.api.controller;

import ewp.tasktracker.api.dto.bug.BugDto;
import ewp.tasktracker.api.dto.bug.CreateBugRq;
import ewp.tasktracker.api.dto.bug.UpdateBugRq;
import ewp.tasktracker.api.dto.page.PageDto;
import ewp.tasktracker.service.bug.BugService;
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
    public ResponseEntity<List<BugDto>> getAll(@RequestParam(value = "pageSize", required = false) Integer pageSize,
                                               @RequestParam(value = "pageNumber") Integer pageNumber) {
        List<BugDto> bugs = bugService.findAll(pageSize, pageNumber);
        return ResponseEntity.ok(bugs);
    }

    @GetMapping("/searchByAssigneeId")
    @ApiOperation(value = "Получить список багов назначенных на пользователя", response = BugDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 404, message = "Сущность не найдена"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<List<BugDto>> getByAssigneeId(@RequestParam String assigneeId) {
        List<BugDto> bugs = bugService.findByAssigneeId(assigneeId);
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

    @PutMapping
    @ApiOperation(value = "Обновить баг", response = BugDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 404, message = "Сущность не найдена"),
            @ApiResponse(code = 422, message = "Ошибка в валидации"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<BugDto> update(@Validated @RequestBody UpdateBugRq dto) {
        BugDto bugDto = bugService.update(dto);
        return ResponseEntity.ok(bugDto);
    }

    @GetMapping("/search")
    @ApiOperation(value = "Поиск бага по имени", response = BugDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<PageDto<BugDto>> getByName(@RequestParam(value = "filter") String filter,
                                                     @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                     @RequestParam(value = "pageNumber") Integer pageNumber) {
        PageDto<BugDto> bugDto = bugService.findByName(filter, pageSize, pageNumber);
        return ResponseEntity.ok(bugDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Удалить баг", response = BugDto.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 404, message = "Сущность не найдена"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<BugDto> delete(@PathVariable String id) {
        BugDto bugDto = bugService.delete(id);
        return ResponseEntity.ok(bugDto);
    }
}

