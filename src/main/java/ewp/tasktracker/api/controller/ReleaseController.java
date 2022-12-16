package ewp.tasktracker.api.controller;

import ewp.tasktracker.api.dto.CreateReleaseRq;
import ewp.tasktracker.api.dto.ReleaseDto;
import ewp.tasktracker.entity.ReleaseEntity;
import ewp.tasktracker.service.ReleaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/release",
        produces = {MediaType.APPLICATION_JSON_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Api(value = "task-tracker", tags = {"release"})
public class ReleaseController {

    private final ReleaseService releaseService;

    @GetMapping
    @ApiOperation(value = "Получить список релизов", response = ReleaseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<List<ReleaseDto>> getAll() {
        List<ReleaseDto> releases = releaseService.findAll().stream().map(ReleaseDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(releases);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Получить релиз по id", response = ReleaseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 404, message = "Сущность не найдена"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<ReleaseDto> getById(@PathVariable String id) {
        ReleaseEntity releaseEntity = releaseService.findById(id);
        return ResponseEntity.ok(new ReleaseDto(releaseEntity));
    }

    @PostMapping
    @ApiOperation(value = "Сохранить релиз", response = ReleaseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<ReleaseDto> save(@RequestBody CreateReleaseRq dto) {
        ReleaseEntity releaseEntity = releaseService.save(dto);
        return ResponseEntity.ok(new ReleaseDto(releaseEntity));
    }
}
