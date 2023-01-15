package ewp.tasktracker.api.controller.comments;

import com.fasterxml.jackson.databind.ObjectMapper;
import ewp.tasktracker.api.controller.CommentController;
import ewp.tasktracker.api.dto.comment.CommentDto;
import ewp.tasktracker.api.dto.comment.CreateCommentRq;
import ewp.tasktracker.service.comment.CommentServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CommentController.class)
public class CommentControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentServiceImpl service;

    @Autowired
    private ObjectMapper objectMapper;
    private final static String ID = "12345";
    private final static String TEXT = "Test comment";
    private final static String TASK_ID = "Task id";
    private final static int PAGE_SIZE = 20;
    private final static int PAGE_NUMBER = 0;

    @Test
    @DisplayName("Successful get all Comments")
    void getAllComments() throws Exception {
        List<CommentDto> dtoList = getListDto();
        when(service.findAll(PAGE_SIZE, PAGE_NUMBER)).thenReturn(dtoList);
        mockMvc.perform(get("/api/comments&pageNumber=" + PAGE_NUMBER + "&pageSize=" + PAGE_SIZE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    private CreateCommentRq getCreateCommentRq() {
        CreateCommentRq commentRq = new CreateCommentRq();
        commentRq.setText(TEXT);
        commentRq.setTaskId(TASK_ID);
        return commentRq;
    }

    @Test
    @DisplayName("Successful create Comment")
    void createComment() throws Exception {
        CreateCommentRq request = getCreateCommentRq();
        CommentDto result = new CommentDto(request.toEntity());
        when(service.save(request)).thenReturn(result);
        mockMvc.perform(post("/api/comments")
                .content(new ObjectMapper().writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text", equalTo(TEXT)));
    }

    private List<CommentDto> getListDto() {
        return List.of(new CommentDto(getCreateCommentRq().toEntity()),
        new CommentDto(getCreateCommentRq().toEntity()),
        new CommentDto(getCreateCommentRq().toEntity()));
    }
}
