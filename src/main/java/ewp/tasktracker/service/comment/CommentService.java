package ewp.tasktracker.service.comment;

import ewp.tasktracker.api.dto.comment.CommentDto;
import ewp.tasktracker.api.dto.comment.CreateCommentRq;

import java.util.List;

public interface CommentService {
    CommentDto save(CreateCommentRq comment);
    CommentDto findById(String id);
    List<CommentDto> findAll();
}
