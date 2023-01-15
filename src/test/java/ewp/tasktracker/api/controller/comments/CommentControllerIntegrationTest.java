package ewp.tasktracker.api.controller.comments;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ewp.tasktracker.api.dto.comment.CreateCommentRq;
import ewp.tasktracker.entity.CommentEntity;
import ewp.tasktracker.repository.CommentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class CommentControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CommentRepository repository;
    private ObjectMapper objectMapper;
    private final static String ID = "234";
    private final static String TEXT = "Text comment";
    private final static String TASK_ID = "Task id";
    private final static int PAGE_NUMBER = 0;
    private final static int PAGE_SIZE = 20;

    @BeforeEach
    void init() {
        repository.deleteAll();
        repository.saveAll(getListEntities());
    }

    @AfterEach
    void cleanTable() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("Successful get all comments")
    void getAllComments() throws Exception {
        mockMvc.perform(get("/api/comments?pageNumber=" + PAGE_NUMBER + "&pageSize=" + PAGE_SIZE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    @DisplayName("Successful create comment")
    void createCommentShouldReturnStatusOk() throws Exception {
        CreateCommentRq rq = getCreateCommentRq();
        mockMvc.perform(post("/api/comments")
                .content(objectMapper.writeValueAsString(rq))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text", equalTo(TEXT)));
    }

    private CreateCommentRq getCreateCommentRq() {
        CreateCommentRq rq = new CreateCommentRq();
        rq.setText(TEXT);
        rq.setTaskId(TASK_ID);
        return rq;
    }

    List<CommentEntity> getListEntities() {
        CommentEntity entity = getCreateCommentRq().toEntity();
        return List.of(entity, entity, entity);
    }
}
