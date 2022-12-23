package ewp.tasktracker.api.controller;

import ewp.tasktracker.api.dto.CreateDeliveryRq;
import ewp.tasktracker.api.dto.DeliveryDto;
import ewp.tasktracker.entity.DeliveryEntity;
import ewp.tasktracker.service.DeliveryService;
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
@RequestMapping(value = "/api/delivery",
        produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Api(value = "task-tracker", tags = {"delivery"})
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping
    @ApiOperation(value = "Получить список поставок", response = DeliveryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<List<DeliveryDto>> getAll() {
        List<DeliveryDto> releases = deliveryService.findAll().stream().map(DeliveryDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(releases);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Получить поставку по id", response = DeliveryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 404, message = "Сущность не найдена"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<DeliveryDto> getById(@PathVariable String id) {
        DeliveryEntity deliveryEntity = deliveryService.findById(id);
        return ResponseEntity.ok(new DeliveryDto(deliveryEntity));
    }

    @PostMapping
    @ApiOperation(value = "Сохранить поставку", response = DeliveryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный ответ"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервиса")
    })
    public ResponseEntity<DeliveryDto> save(@RequestBody CreateDeliveryRq dto) {
        DeliveryEntity deliveryEntity = deliveryService.save(dto);
        return ResponseEntity.ok(new DeliveryDto(deliveryEntity));
    }
}
