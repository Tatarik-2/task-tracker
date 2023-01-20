package ewp.tasktracker.api.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import ewp.tasktracker.api.dto.sprint.CreateSprintRq;
import ewp.tasktracker.api.dto.sprint.UpdateSprintRq;
import ewp.tasktracker.entity.SprintEntity;
import ewp.tasktracker.repository.SprintRepository;
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

import java.time.LocalDateTime;
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
public class SprintControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SprintRepository repository;

    @Autowired
    private ObjectMapper objectMapper;
    private static final String NAME = "SPRINT";
    private static final String NAME_2 = "SPRINT2";
    private static final String SUPERSPRINT_ID = "123412341234";
    private static final String AUTHOR_ID = "123412341234";
    private static final LocalDateTime START_AT = LocalDateTime.MIN;
    private static final LocalDateTime END_AT = LocalDateTime.MAX;
    private static final String ID = "123412341234";
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
    @DisplayName("Positive get all Sprints")
    void getAllSprintsShouldReturnOkStatus() throws Exception {
        mockMvc.perform(get("/api/sprint?pageNumber=" + PAGE_NUMBER + "&pageSize=" + PAGE_SIZE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    @DisplayName("Positive get Sprint by Id")
    void getSprintByIdShouldReturnOkStatus() throws Exception {
        mockMvc.perform(get("/api/sprint/" + ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(ID)));
    }

    @Test
    @DisplayName("Negative get Sprint by Id (not found)")
    void getSprintByIdShouldThrowException() throws Exception {
        mockMvc.perform(get("/api/sprint/" + ID + "1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Positive create Sprint")
    void createSprintShouldReturnOkStatus() throws Exception {
        CreateSprintRq createSprintRq = getCreateSprintRq();
        createSprintRq.setName(NAME_2);
        mockMvc.perform(post("/api/sprint")
                        .content(objectMapper.writeValueAsString(createSprintRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME_2)));
    }

    @Test
    @DisplayName("Negative create Sprint (failed validation)")
    void createSprintShouldThrowException() throws Exception {
        CreateSprintRq createSprintRq = getCreateSprintRq();
        createSprintRq.setName(null);
        mockMvc.perform(post("/api/sprint")
                        .content(objectMapper.writeValueAsString(createSprintRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(422));
    }

    @Test
    @DisplayName("Positive update Sprint")
    void updateSprintShouldReturnOkStatus() throws Exception {
        UpdateSprintRq updateSprintRq = getUpdateSprintRq();
        updateSprintRq.setName(NAME_2);
        mockMvc.perform(put("/api/sprint")
                        .content(objectMapper.writeValueAsString(updateSprintRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME_2)));
    }

    @Test
    @DisplayName("Negative update Sprint (not found)")
    void updateSprintShouldThrowException() throws Exception {
        UpdateSprintRq updateSprintRq = getUpdateSprintRq();
        updateSprintRq.setId(ID + "1");
        mockMvc.perform(put("/api/sprint")
                        .content(objectMapper.writeValueAsString(updateSprintRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Negative update Sprint (failed validation)")
    void updateSprintShouldThrowExceptionValidation() throws Exception {
        UpdateSprintRq updateSprintRq = getUpdateSprintRq();
        updateSprintRq.setName(null);
        mockMvc.perform(put("/api/sprint")
                        .content(objectMapper.writeValueAsString(updateSprintRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(422));
    }

    private CreateSprintRq getCreateSprintRq() {
        CreateSprintRq createSprintRq = new CreateSprintRq();
        createSprintRq.setName(NAME);
        createSprintRq.setSuperSprintId(SUPERSPRINT_ID);
        createSprintRq.setAuthorId(AUTHOR_ID);
        createSprintRq.setStartAt(START_AT);
        createSprintRq.setEndAt(END_AT);

        return createSprintRq;
    }

    private UpdateSprintRq getUpdateSprintRq() {
        UpdateSprintRq sprintRq = new UpdateSprintRq();
        sprintRq.setId(ID);
        sprintRq.setName(NAME);
        sprintRq.setSuperSprintId(SUPERSPRINT_ID);
        sprintRq.setAuthorId(AUTHOR_ID);
        sprintRq.setStartAt(START_AT);
        sprintRq.setEndAt(END_AT);
        return sprintRq;
    }

    private List<SprintEntity> getListEntities() {
        SprintEntity entity = getCreateSprintRq().toEntity();
        entity.setId(ID);
        return List.of(entity,
                getCreateSprintRq().toEntity(),
                getCreateSprintRq().toEntity());
    }
}
