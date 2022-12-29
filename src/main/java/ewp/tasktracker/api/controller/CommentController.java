package ewp.tasktracker.api.controller;

import ewp.tasktracker.api.dto.comment.CommentDto;
import ewp.tasktracker.api.dto.comment.CreateCommentRq;
import ewp.tasktracker.api.dto.comment.UpdateCommentRq;
import ewp.tasktracker.service.comment.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(
        value = "/api/comments",
        produces = {MediaType.APPLICATION_JSON_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE}
)
@Api(value = "task-tracker", tags = {"comment"})
@Validated
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    @ApiOperation(value = "Сохранить", response = CommentDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Успешное создание комментария"),
            @ApiResponse(code = 500, message = "Внутрення ошибка сервера"),
            @ApiResponse(code = 422, message = "Неподдерживаемый формат")
    })
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CreateCommentRq request) {
        CommentDto response = commentService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping
    @ApiOperation(value = "Обновить", response = CommentDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Успешное обновление комментария"),
            @ApiResponse(code = 500, message = "Внутрення ошибка сервера"),
            @ApiResponse(code = 422, message = "Неподдерживаемый формат"),
            @ApiResponse(code = 404, message = "Комментарий не найден")
    })
    public ResponseEntity<CommentDto> updateComment(@Valid @RequestBody UpdateCommentRq request) {
        CommentDto response = commentService.update(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Удалить комментарий", response = CommentDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Успешное удаление комментария"),
            @ApiResponse(code = 500, message = "Внутрення ошибка сервера"),
            @ApiResponse(code = 404, message = "Комментарий не найден")
    })
    public ResponseEntity<CommentDto> deleteComment(@PathVariable String id) {
        CommentDto response = commentService.deleteById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
