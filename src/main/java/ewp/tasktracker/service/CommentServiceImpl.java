package ewp.tasktracker.service;

import ewp.tasktracker.api.dto.CommentDto;
import ewp.tasktracker.api.dto.CreateCommentRq;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
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
    public List<CommentDto> findAll() {
        return commentRepository
                .findAll().stream().map(CommentDto::new)
                .collect(Collectors.toList());
    }
}
