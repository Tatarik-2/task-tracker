package ewp.tasktracker.service.comment;

import ewp.tasktracker.api.dto.comment.CommentDto;
import ewp.tasktracker.api.dto.comment.CreateCommentRq;
import ewp.tasktracker.api.dto.comment.UpdateCommentRq;
import ewp.tasktracker.entity.CommentEntity;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.CommentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CommentServiceIntegrationTest {
    @Autowired
    private CommentRepository repository;
    @Autowired
    private CommentServiceImpl service;
    private final static String ID = "1224";
    private final static String TEXT = "Test text";
    private final static String TASK_ID = "Task id";
    private final static int PAGE_NUMBER = 0;
    private final static int PAGE_SIZE = 20;

    @BeforeEach
    void init() {
        repository.deleteAll();
        repository.saveAll(getLisEntities());
    }

    @AfterEach
    void cleanTable() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("Successful save Comment")
    void saveCommentShouldReturnEntity() {
        CommentDto dto = service.save(getCreateCommentRq());
        assertAll(
                () -> assertEquals(CommentDto.class, dto.getClass()),
                () -> assertEquals(TEXT, dto.getText()),
                () -> assertEquals(TASK_ID, dto.getTaskId())
        );
    }

    @Test
    @DisplayName("Successful find all Comments")
    void findAllComments() {
        List<CommentDto> dtoList = service.findAll(PAGE_SIZE, PAGE_NUMBER);
        assertEquals(3, dtoList.size());
    }

    @Test
    @DisplayName("Successful update comment")
    void updateComment() {
        UpdateCommentRq update = getUpdateCommentRq();
        update.setId("1");
        assertThrows(ResourceNotFoundException.class, () -> service.update(update));
    }

    private CreateCommentRq getCreateCommentRq() {
        CreateCommentRq rq = new CreateCommentRq();
        rq.setText(TEXT);
        rq.setTaskId(TASK_ID);
        return rq;
    }

    private List<CommentEntity> getLisEntities() {
        CommentEntity entity = getCreateCommentRq().toEntity();
        entity.setId(ID);
        return List.of(entity, entity, entity);
    }

    public UpdateCommentRq getUpdateCommentRq() {
        UpdateCommentRq update = new UpdateCommentRq();
        update.setText(TEXT);
        update.setTaskId(TASK_ID);
        return update;
    }
}
