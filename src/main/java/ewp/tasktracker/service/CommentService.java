package ewp.tasktracker.service;

import ewp.tasktracker.api.dto.comment.CommentDto;
import ewp.tasktracker.api.dto.comment.CreateCommentRq;
import ewp.tasktracker.api.dto.comment.UpdateCommentRq;

public interface CommentService {
    CommentDto save(CreateCommentRq comment);
    CommentDto update(UpdateCommentRq comment);
    CommentDto getById(String id);
}
