package ewp.tasktracker.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ewp.tasktracker.api.dto.bug.CreateBugRq;
import ewp.tasktracker.api.dto.bug.UpdateBugRq;
import ewp.tasktracker.entity.BugEntity;
import ewp.tasktracker.entity.common.Priority;
import ewp.tasktracker.entity.common.ProgressStatus;
import ewp.tasktracker.repository.BugRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class BugControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BugRepository repository;
    @Autowired
    private ObjectMapper objectMapper;
    private static final String NAME = "Test name";
    private static final String NAME_2 = "Test name another";
    private static final String DESCRIPTION = "Test description";
    private static final String HISTORY_ID = "Test history";
    private static final String AUTHOR_ID = "Test authorId";
    private static final String ASSIGNEE_ID = "Test assigneeId";
    private static final String SPRINT_ID = "Test sprintId";
    private static final String ID = "12345";
    private static final int PAGE_NUMBER = 0;
    private static final int PAGE_SIZE = 20;

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
    @DisplayName("Positive get all Bugs")
    void getAllShouldReturnOkStatus() throws Exception {
        mockMvc.perform(get("/api/bug?pageNumber=" + PAGE_NUMBER + "&pageSize=" + PAGE_SIZE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    @DisplayName("Positive get Bug by Id")
    void getByIdShouldReturnOkStatus() throws Exception {
        mockMvc.perform(get("/api/bug/" + ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(ID)));
    }

    @Test
    @DisplayName("Negative get Bug by Id")
    void getByIdShouldThrowException() throws Exception {
        mockMvc.perform(get("/api/bug/" + ID + "1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Positive create Bug")
    void createShouldReturnOkStatus() throws Exception {
        CreateBugRq createBugRq = getCreateBugRq();
        createBugRq.setName(NAME_2);
        mockMvc.perform(post("/api/bug")
                        .content(objectMapper.writeValueAsString(createBugRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME_2)));
    }


    @Test
    @DisplayName("Positive update Bug")
    void updateShouldReturnOkStatus() throws Exception {
        UpdateBugRq updateBugRq = getUpdateBugRq();
        updateBugRq.setName(NAME_2);
        mockMvc.perform(put("/api/bug")
                        .content(objectMapper.writeValueAsString(updateBugRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME_2)));
    }

    @Test
    @DisplayName("Negative update Bug")
    void updateShouldThrowException() throws Exception {
        UpdateBugRq updateBugRq = getUpdateBugRq();
        updateBugRq.setId(ID + "1");
        mockMvc.perform(put("/api/bug")
                        .content(objectMapper.writeValueAsString(updateBugRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Positive get Bug by Name")
    void getByNameShouldReturnOkStatus() throws Exception {
        mockMvc.perform(get("/api/bug/search?filter=" + NAME + "&pageNumber="
                        + PAGE_NUMBER + "&pageSize=" + PAGE_SIZE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total", equalTo(3)));
    }

    @Test
    @DisplayName("Negative get Bug by Name")
    void getByNameShouldThrowException() throws Exception {
        mockMvc.perform(get("/api/bug/search?filter=" + NAME + "1" + "&pageNumber="
                        + PAGE_NUMBER + "&pageSize=" + PAGE_SIZE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total", equalTo(0)));
    }


    private CreateBugRq getCreateBugRq() {
        CreateBugRq createBugRq = new CreateBugRq();
        createBugRq.setName(NAME);
        createBugRq.setPriority(Priority.HIGH);
        createBugRq.setStatus(ProgressStatus.IN_PROGRESS);
        createBugRq.setDescription(DESCRIPTION);
        createBugRq.setAuthorId(AUTHOR_ID);
        createBugRq.setAssigneeId(ASSIGNEE_ID);
        createBugRq.setHistoryId(HISTORY_ID);
        createBugRq.setSprintId(SPRINT_ID);
        return createBugRq;
    }

    private UpdateBugRq getUpdateBugRq() {
        UpdateBugRq updateBugRq = new UpdateBugRq();
        updateBugRq.setId(ID);
        updateBugRq.setName(NAME);
        updateBugRq.setPriority(Priority.HIGH);
        updateBugRq.setStatus(ProgressStatus.IN_PROGRESS);
        updateBugRq.setDescription(DESCRIPTION);
        updateBugRq.setAuthorId(AUTHOR_ID);
        updateBugRq.setAssigneeId(ASSIGNEE_ID);
        updateBugRq.setHistoryId(HISTORY_ID);
        updateBugRq.setSprintId(SPRINT_ID);
        return updateBugRq;
    }

    private List<BugEntity> getListEntities() {
        BugEntity entity = getCreateBugRq().toEntity();
        entity.setId(ID);
        return List.of(entity,
                getCreateBugRq().toEntity(),
                getCreateBugRq().toEntity());
    }
}