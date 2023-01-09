package ewp.tasktracker.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ewp.tasktracker.api.dto.history.CreateHistoryRq;
import ewp.tasktracker.api.dto.history.UpdateHistoryRq;
import ewp.tasktracker.entity.HistoryEntity;
import ewp.tasktracker.entity.common.Priority;
import ewp.tasktracker.entity.common.ProgressStatus;
import ewp.tasktracker.repository.HistoryRepository;
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
class HistoryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HistoryRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String NAME = "Test name";
    private static final String NAME_2 = "Test name another";
    private static final String DESCRIPTION = "Test description";
    private static final String EPIC_ID = "Test epicId";
    private static final String AUTHOR_ID = "Test authorId";
    private static final String SPRINT_ID = "Test sprintId";
    private static final String ID = "12345";
    private static final int pageNumber = 0;
    private static final int pageSize = 20;

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
    @DisplayName("Positive get all Histories")
    void getAllHistoriesShouldReturnOkStatus() throws Exception {
        mockMvc.perform(get("/api/history?pageNumber=" + pageNumber + "&pageSize=" + pageSize))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    @DisplayName("Positive get History by Id")
    void getHistoryByIdShouldReturnOkStatus() throws Exception {
        mockMvc.perform(get("/api/history/" + ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(ID)));
    }

    @Test
    @DisplayName("Negative get History by Id (not found)")
    void getHistoryByIdShouldThrowException() throws Exception {
        mockMvc.perform(get("/api/history/" + ID + "1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Positive create History")
    void createHistoryShouldReturnOkStatus() throws Exception {
        CreateHistoryRq createHistoryRq = getCreateHistoryRq();
        createHistoryRq.setName(NAME_2);
        mockMvc.perform(post("/api/history")
                        .content(objectMapper.writeValueAsString(createHistoryRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME_2)));
    }

    @Test
    @DisplayName("Negative create History (failed validation)")
    void createHistoryShouldThrowException() throws Exception {
        CreateHistoryRq createHistoryRq = getCreateHistoryRq();
        createHistoryRq.setName(null);
        mockMvc.perform(post("/api/history")
                        .content(objectMapper.writeValueAsString(createHistoryRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(422));
    }

    @Test
    @DisplayName("Positive update History")
    void updateHistoryShouldReturnOkStatus() throws Exception {
        UpdateHistoryRq updateHistoryRq = getUpdateHistoryRq();
        updateHistoryRq.setName(NAME_2);
        mockMvc.perform(put("/api/history")
                        .content(objectMapper.writeValueAsString(updateHistoryRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME_2)));
    }

    @Test
    @DisplayName("Negative update History (not found)")
    void updateHistoryShouldThrowException() throws Exception {
        UpdateHistoryRq updateHistoryRq = getUpdateHistoryRq();
        updateHistoryRq.setId(ID + "1");
        mockMvc.perform(put("/api/history")
                        .content(objectMapper.writeValueAsString(updateHistoryRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Negative update History (failed validation)")
    void updateHistoryShouldThrowExceptionValidation() throws Exception {
        UpdateHistoryRq updateHistoryRq = getUpdateHistoryRq();
        updateHistoryRq.setName(null);
        mockMvc.perform(put("/api/history")
                        .content(objectMapper.writeValueAsString(updateHistoryRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(422));
    }

    @Test
    @DisplayName("Positive get History by Name")
    void getHistoryByNameShouldReturnOkStatus() throws Exception {
        mockMvc.perform(get("/api/history/search?filter=" + NAME + "&pageNumber="
                        + pageNumber + "&pageSize=" + pageSize))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total", equalTo(3)));
    }

    @Test
    @DisplayName("Negative get History by Name (empty PageDto)")
    void getHistoryByNameShouldThrowException() throws Exception {
        mockMvc.perform(get("/api/history/search?filter=" + NAME + "1" + "&pageNumber="
                        + pageNumber + "&pageSize=" + pageSize))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total", equalTo(0)));
    }

    private CreateHistoryRq getCreateHistoryRq() {
        CreateHistoryRq createHistoryRq = new CreateHistoryRq();
        createHistoryRq.setName(NAME);
        createHistoryRq.setPriority(Priority.HIGH);
        createHistoryRq.setStatus(ProgressStatus.IN_PROGRESS);
        createHistoryRq.setDescription(DESCRIPTION);
        createHistoryRq.setSprintId(SPRINT_ID);
        createHistoryRq.setAuthorId(AUTHOR_ID);
        createHistoryRq.setEpicId(EPIC_ID);
        return createHistoryRq;
    }

    private UpdateHistoryRq getUpdateHistoryRq() {
        UpdateHistoryRq historyRq = new UpdateHistoryRq();
        historyRq.setId(ID);
        historyRq.setName(NAME);
        historyRq.setPriority(Priority.HIGH);
        historyRq.setStatus(ProgressStatus.IN_PROGRESS);
        historyRq.setDescription(DESCRIPTION);
        historyRq.setSprintId(SPRINT_ID);
        historyRq.setAuthorId(AUTHOR_ID);
        historyRq.setEpicId(EPIC_ID);
        return historyRq;
    }

    private List<HistoryEntity> getListEntities() {
        HistoryEntity entity = getCreateHistoryRq().toEntity();
        entity.setId(ID);
        return List.of(entity,
                getCreateHistoryRq().toEntity(),
                getCreateHistoryRq().toEntity());
    }
}