package ewp.tasktracker.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ewp.tasktracker.api.dto.comment.CreateCommentRq;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@ExtendWith(SpringExtension.class)
@WebMvcTest(value = CommentController.class)
public class TestCommentController {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CommentController commentController;

    @Test
    @DisplayName("Тестовый запрос на создание комментария")
    public void testCommentCreate() throws Exception {
        CreateCommentRq rq = new CreateCommentRq("Test comment", "task id");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(rq.toString())).andDo(print());
    }
}
