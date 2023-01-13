package ewp.tasktracker.service.comment;

import ewp.tasktracker.api.dto.comment.CommentDto;
import ewp.tasktracker.api.dto.comment.CreateCommentRq;
import ewp.tasktracker.api.util.PageUtil;
import ewp.tasktracker.entity.CommentEntity;
import ewp.tasktracker.repository.CommentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CommentServiceUnitTest {
    private final static String ID = "1234";
    private final static String TEXT = "test comment";
    private final static String TASK_ID = "Id";
    private final static int PAGE_NUMBER = 0;
    private final static int PAGE_SIZE = 20;
    @MockBean
    private PageUtil pageUtil;
    @Autowired
    private CommentServiceImpl service;
    @MockBean
    private CommentRepository repository;
    @Mock
    private CreateCommentRq createCommentRq;

    @Test
    @DisplayName("Successful create comment")
    void createComment() {
        CommentEntity entity = getEntity();
        when(createCommentRq.toEntity()).thenReturn(entity);
        when(repository.save(any())).thenReturn(entity);
        CommentDto dto = service.save(createCommentRq);
        Assertions.assertAll(
                () -> assertEquals(CommentDto.class, dto.getClass()),
                () -> assertEquals(ID, dto.getId()),
                () -> assertEquals(TEXT, dto.getText()),
                () -> assertEquals(TASK_ID, dto.getTaskId())
        );
    }

    @Test
    @DisplayName("Successful get all comments with pagination")
    void getAllComments() {
        List<CommentEntity> comments = List.of(getEntity(), getEntity(), getEntity());
        when(repository.findAll(PageRequest.of(PAGE_NUMBER, PAGE_SIZE))).thenReturn(new PageImpl<>(comments));
        when(pageUtil.pageSizeControl(PAGE_SIZE)).thenReturn(PAGE_SIZE);
        List<CommentDto> dtoList = service.findAll(PAGE_SIZE, PAGE_NUMBER);
        assertEquals(3, dtoList.size());
    }

    private CommentEntity getEntity() {
        CommentEntity entity = new CommentEntity();
        entity.setId(ID);
        entity.setText(TEXT);
        entity.setTaskId(TASK_ID);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAd(LocalDateTime.now());
        return entity;
    }
}
