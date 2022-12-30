package ewp.tasktracker.service.comment;

import ewp.tasktracker.api.dto.comment.CommentDto;
import ewp.tasktracker.api.dto.comment.CreateCommentRq;
import ewp.tasktracker.api.dto.comment.UpdateCommentRq;
import ewp.tasktracker.api.util.PageUtil;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PageUtil pageUtil;

    @Override
    public CommentDto save(CreateCommentRq dto) {
        return new CommentDto(commentRepository.save(dto.toEntity()));
    }

    @Override
    public CommentDto findById(String id) {
        return new CommentDto(
                commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found, id: " + id))
        );
    }

    @Override
    public List<CommentDto> findAll(Integer pageSize, Integer pageNumber) {
        pageSize = pageUtil.pageSizeControl(pageSize);
        return commentRepository.findAll(PageRequest.of(pageNumber, pageSize))
                .stream().map(CommentDto::new).collect(Collectors.toList());
    }

    @Override
    public CommentDto update(UpdateCommentRq request) {
        CommentDto comment = findById(request.getId());
        comment.setText(request.getText());
        comment.setTaskId(request.getTaskId());
        return comment;
    }

    @Override
    public CommentDto deleteById(String id) {
        CommentDto comment = findById(id);
        commentRepository.deleteById(id);
        return comment;
    }
}
