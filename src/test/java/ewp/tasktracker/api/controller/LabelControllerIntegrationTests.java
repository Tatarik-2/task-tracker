package ewp.tasktracker.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ewp.tasktracker.api.dto.label.CreateLabelRq;
import ewp.tasktracker.api.dto.label.UpdateLabelRq;
import ewp.tasktracker.entity.LabelsEntity;
import ewp.tasktracker.repository.LabelsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LabelControllerIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LabelsRepository labelsRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String ID = "420";
    private static final String TEXT = "blablabla";
    private static final String TEXT_UPD = "UPDATED";
    private static final String AUTHOR_ID = "34";
    private static final String TASK_ID = "34";
    private static final int PAGE_NUMBER = 0;
    private static final int PAGE_SIZE = 20;

    @BeforeEach
    void init() {
        labelsRepository.deleteAll();
        labelsRepository.saveAll(getListEntities());
    }

    @AfterEach
    void cleanTable() {
        labelsRepository.deleteAll();
    }

    @Test
    @DisplayName("Positive get all Histories")
    void getAllHistoriesShouldReturnOkStatus() throws Exception {
        mockMvc.perform(get("/api/labels?pageNumber=" + PAGE_NUMBER + "&pageSize=" + PAGE_SIZE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    @DisplayName("Positive get Label by Id")
    void getLabelByIdShouldReturnOkStatus() throws Exception {
        mockMvc.perform(get("/api/labels/" + ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(ID)));
    }

    @Test
    @DisplayName("Negative get Label by Id (not found)")
    void getLabelByIdShouldThrowException() throws Exception {
        mockMvc.perform(get("/api/labels/" + ID + "1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Positive create Label")
    void createLabelShouldReturnOkStatus() throws Exception {
        CreateLabelRq createLabelRq = getCreateLabelsRq();
        createLabelRq.setText(TEXT);
        mockMvc.perform(post("/api/labels")
                        .content(objectMapper.writeValueAsString(createLabelRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text", equalTo(TEXT)));
    }

    @Test
    @DisplayName("Negative create Label (failed validation)")
    void createLabelShouldThrowException() throws Exception {
        CreateLabelRq createLabelRq = getCreateLabelsRq();
        createLabelRq.setText(null);
        mockMvc.perform(post("/api/labels")
                        .content(objectMapper.writeValueAsString(createLabelRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(422));
    }

    @Test
    @DisplayName("Positive update Label")
    void updateLabelShouldReturnOkStatus() throws Exception {
        UpdateLabelRq updateLabelRq = getUpdateLabelsRq();
        updateLabelRq.setText(TEXT_UPD);
        mockMvc.perform(put("/api/labels")
                        .content(objectMapper.writeValueAsString(updateLabelRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text", equalTo(TEXT_UPD)));
    }

    @Test
    @DisplayName("Negative update Label (not found)")
    void updateLabelShouldThrowException() throws Exception {
        UpdateLabelRq updateLabelRq = getUpdateLabelsRq();
        updateLabelRq.setId(ID + "1");
        mockMvc.perform(put("/api/labels")
                        .content(objectMapper.writeValueAsString(updateLabelRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Negative update Label (failed validation)")
    void updateLabelShouldThrowExceptionValidation() throws Exception {
        UpdateLabelRq updateLabelRq = getUpdateLabelsRq();
        updateLabelRq.setText(null);
        mockMvc.perform(put("/api/labels")
                        .content(objectMapper.writeValueAsString(updateLabelRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(422));
    }
    private CreateLabelRq getCreateLabelsRq() {
        CreateLabelRq createLabelRq = new CreateLabelRq();
        createLabelRq.setTaskId(TASK_ID);
        createLabelRq.setAuthorId(AUTHOR_ID);
        createLabelRq.setText(TEXT);
        return createLabelRq;
    }

    private UpdateLabelRq getUpdateLabelsRq() {
        UpdateLabelRq updateLabelRq = new UpdateLabelRq();
        updateLabelRq.setId(ID);
        updateLabelRq.setTaskId(TASK_ID);
        updateLabelRq.setAuthorId(AUTHOR_ID);
        updateLabelRq.setText(TEXT);
        return updateLabelRq;
    }

    private List<LabelsEntity> getListEntities() {
        LabelsEntity entity = getCreateLabelsRq().toEntity();
        entity.setId(ID);
        return List.of(entity,
                getCreateLabelsRq().toEntity(),
                getCreateLabelsRq().toEntity());
    }

}
