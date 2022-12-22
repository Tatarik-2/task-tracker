package ewp.tasktracker.service;

import ewp.tasktracker.api.dto.CommentDto;
import ewp.tasktracker.api.dto.CreateCommentRq;

import java.util.List;

public interface CommentService {
    CommentDto save(CreateCommentRq comment);
    CommentDto findById(String id);
    List<CommentDto> findAll();
}
