package ewp.tasktracker.service;

import ewp.tasktracker.api.dto.comment.CommentDto;
import ewp.tasktracker.api.dto.comment.CreateCommentRq;
import ewp.tasktracker.api.dto.comment.UpdateCommentRq;
import ewp.tasktracker.entity.CommentEntity;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.module.ResolutionException;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    @Override
    public CommentDto save(CreateCommentRq dto) {
        return new CommentDto(commentRepository.save(dto.toEntity()));
    }
    @Override
    @Transactional
    public CommentDto update(UpdateCommentRq comment) {
        CommentEntity entity = commentRepository.findById(comment.getId()).orElseThrow(() -> {
            throw new ResourceNotFoundException("Comment doesn't exist id: " + comment.getId());
        });
        CommentEntity updated = comment.toEntity();
        updated.setCreatedAt(entity.getCreatedAt());
        return new CommentDto(commentRepository.save(updated));
    }

    @Override
    public CommentDto getById(String id) {
        return new CommentDto(commentRepository.findById(id)
                .orElseThrow(() -> {throw new ResolutionException("No entity");}));
    }
}
