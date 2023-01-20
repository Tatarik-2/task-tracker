package ewp.tasktracker.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ewp.tasktracker.api.dto.epic.CreateEpicRq;
import ewp.tasktracker.api.dto.epic.UpdateEpicRq;
import ewp.tasktracker.entity.EpicEntity;
import ewp.tasktracker.entity.common.Priority;
import ewp.tasktracker.entity.common.ProgressStatus;
import ewp.tasktracker.repository.EpicRepository;
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
class EpicControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EpicRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String ID = "12345";
    private static final String NAME = "Test name";
    private static final String NAME_2 = "Test name another";
    private static final String DESCRIPTION = "Test description";
    private static final String PROJECT_ID = "Test projectId";
    private static final String AUTHOR_ID = "Test authorId";
    private static final String SUPERSPRINT_ID = "Test supersprintId";

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
    @DisplayName("Positive get all epics")
    void getAllEpicsShouldReturnOkStatus() throws Exception {
        mockMvc.perform(get("/api/epic?pageNumber=" + PAGE_NUMBER + "&pageSize=" + PAGE_SIZE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    @DisplayName("Positive get epic by Id")
    void getEpicByIdShouldReturnOkStatus() throws Exception {
        mockMvc.perform(get("/api/epic/" + ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(ID)));
    }

    @Test
    @DisplayName("Negative get epic by Id (not found)")
    void getEpicByIdShouldThrowException() throws Exception {
        mockMvc.perform(get("/api/epic/" + ID + "1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Positive create epic")
    void createEpicShouldReturnOkStatus() throws Exception {
        CreateEpicRq createEpicRq = getCreateEpicRq();
        createEpicRq.setName(NAME_2);
        mockMvc.perform(post("/api/epic")
                        .content(objectMapper.writeValueAsString(createEpicRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME_2)));
    }

    @Test
    @DisplayName("Negative create epic (failed validation)")
    void createEpicShouldThrowException() throws Exception {
        CreateEpicRq createEpicRq = getCreateEpicRq();
        createEpicRq.setName(null);
        mockMvc.perform(post("/api/epic")
                        .content(objectMapper.writeValueAsString(createEpicRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(422));
    }

    @Test
    @DisplayName("Positive update epic")
    void updateEpicShouldReturnOkStatus() throws Exception {
        UpdateEpicRq updateEpicRq = getUpdateEpicRq();
        updateEpicRq.setName(NAME_2);
        mockMvc.perform(put("/api/epic")
                        .content(objectMapper.writeValueAsString(updateEpicRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME_2)));
    }

    @Test
    @DisplayName("Negative update epic (not found)")
    void updateEpicShouldThrowException() throws Exception {
        UpdateEpicRq updateEpicRq = getUpdateEpicRq();
        updateEpicRq.setId(ID + "1");
        mockMvc.perform(put("/api/epic")
                        .content(objectMapper.writeValueAsString(updateEpicRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Negative update epic (failed validation)")
    void updateEpicShouldThrowExceptionValidation() throws Exception {
        UpdateEpicRq updateEpicRq = getUpdateEpicRq();
        updateEpicRq.setName(null);
        mockMvc.perform(put("/api/epic")
                        .content(objectMapper.writeValueAsString(updateEpicRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(422));
    }

    @Test
    @DisplayName("Positive get epic by Name")
    void getEpicByNameShouldReturnOkStatus() throws Exception {
        mockMvc.perform(get("/api/epic/search?filter=" + NAME + "&pageNumber="
                        + PAGE_NUMBER + "&pageSize=" + PAGE_SIZE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total", equalTo(3)));
    }

    @Test
    @DisplayName("Negative get History by Name (empty PageDto)")
    void getEpicByNameShouldThrowException() throws Exception {
        mockMvc.perform(get("/api/epic/search?filter=" + NAME + "1" + "&pageNumber="
                        + PAGE_NUMBER + "&pageSize=" + PAGE_SIZE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total", equalTo(0)));
    }


    private CreateEpicRq getCreateEpicRq() {
        CreateEpicRq createEpicRq = new CreateEpicRq();
        createEpicRq.setName(NAME);
        createEpicRq.setDescription(DESCRIPTION);
        createEpicRq.setStatus(ProgressStatus.TODO);
        createEpicRq.setPriority(Priority.LOW);
        createEpicRq.setProjectId(PROJECT_ID);
        createEpicRq.setAuthorId(AUTHOR_ID);
        createEpicRq.setSupersprintId(SUPERSPRINT_ID);
        return createEpicRq;
    }

    private UpdateEpicRq getUpdateEpicRq() {
        UpdateEpicRq epicRq = new UpdateEpicRq();
        epicRq.setId(ID);
        epicRq.setName(NAME);
        epicRq.setDescription(DESCRIPTION);
        epicRq.setStatus(ProgressStatus.TODO);
        epicRq.setPriority(Priority.LOW);
        epicRq.setProjectId(PROJECT_ID);
        epicRq.setAuthorId(AUTHOR_ID);
        epicRq.setSupersprintId(SUPERSPRINT_ID);
        return epicRq;
    }
    private List<EpicEntity> getListEntities() {
        EpicEntity entity = getCreateEpicRq().toEntity();
        entity.setId(ID);
        return List.of(entity,
                getCreateEpicRq().toEntity(),
                getCreateEpicRq().toEntity());
    }
}