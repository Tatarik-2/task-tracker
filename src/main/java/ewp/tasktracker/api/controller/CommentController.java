package ewp.tasktracker.api.controller;

import ewp.tasktracker.api.dto.CommentDto;
import ewp.tasktracker.api.dto.CreateCommentRq;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/api/comments",
        produces = {MediaType.APPLICATION_JSON_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE}
)
@Api(value = "task-tracker", tags = {"comment"})
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    @ApiOperation(value = "Схранить комментарий", response = CommentDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Успешное создание комментария"),
            @ApiResponse(code = 500, message = "Внутрення ошибка сервера"),
            @ApiResponse(code = 422, message = "Неподдерживаемый формат")
    })
    public ResponseEntity<CommentDto> createComment(@Validated @RequestBody CreateCommentRq request, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(commentService.save(request), HttpStatus.CREATED);
    }
}
