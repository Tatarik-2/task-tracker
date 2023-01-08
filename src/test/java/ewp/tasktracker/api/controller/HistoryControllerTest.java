package ewp.tasktracker.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ewp.tasktracker.api.dto.history.CreateHistoryRq;
import ewp.tasktracker.api.dto.history.HistoryDto;
import ewp.tasktracker.api.dto.history.UpdateHistoryRq;
import ewp.tasktracker.api.dto.page.PageDto;
import ewp.tasktracker.entity.common.Priority;
import ewp.tasktracker.entity.common.ProgressStatus;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.service.history.HistoryServiceImpl;
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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HistoryController.class)
class HistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private HistoryServiceImpl service;

    private static final String NAME = "Test name";
    private static final String DESCRIPTION = "Test description";
    private static final String EPIC_ID = "Test epicId";
    private static final String AUTHOR_ID = "Test authorId";
    private static final String SPRINT_ID = "Test sprintId";
    private static final String ID = "12345";
    private static final int pageNumber = 0;
    private static final int pageSize = 20;


    @Test
    @DisplayName("Positive get all Histories")
    void getAllHistoriesShouldReturnOkStatus() throws Exception {
        List<HistoryDto> dtoList = List.of(new HistoryDto(getCreateHistoryRq().toEntity()),
                new HistoryDto(getCreateHistoryRq().toEntity()),
                new HistoryDto(getCreateHistoryRq().toEntity()));
        when(service.findAllHistories(pageSize, pageNumber)).thenReturn(dtoList);
        mockMvc.perform(get("/api/history?pageNumber=" + pageNumber + "&pageSize=" + pageSize))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    @DisplayName("Positive get History by Id")
    void getHistoryByIdShouldReturnOkStatus() throws Exception {
        CreateHistoryRq createHistoryRq = getCreateHistoryRq();
        HistoryDto historyDto = new HistoryDto(createHistoryRq.toEntity());
        historyDto.setId(ID);
        when(service.findHistoryById(ID)).thenReturn(historyDto);
        mockMvc.perform(get("/api/history/" + ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(ID)));
    }

    @Test
    @DisplayName("Negative get History by Id (not found)")
    void getHistoryByIdShouldThrowException() throws Exception {
        when(service.findHistoryById(ID)).thenThrow(ResourceNotFoundException.class);
        mockMvc.perform(get("/api/history/" + ID))
                .andExpect(status().is(404));
    }

    @Test
    @DisplayName("Positive create History")
    void createHistoryShouldReturnOkStatus() throws Exception {
        CreateHistoryRq createHistoryRq = getCreateHistoryRq();
        HistoryDto dto = new HistoryDto(createHistoryRq.toEntity());
        when(service.saveHistory(createHistoryRq)).thenReturn(dto);
        mockMvc.perform(post("/api/history")
                        .content(new ObjectMapper().writeValueAsString(createHistoryRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }

    @Test
    @DisplayName("Negative create History(failed validation)")
    void createHistoryShouldThrowException() throws Exception {
        CreateHistoryRq createHistoryRq = getCreateHistoryRq();
        createHistoryRq.setName(null);
        HistoryDto dto = new HistoryDto(createHistoryRq.toEntity());
        when(service.saveHistory(createHistoryRq)).thenReturn(dto);
        mockMvc.perform(post("/api/history")
                        .content(new ObjectMapper().writeValueAsString(createHistoryRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(422));
    }

    @Test
    @DisplayName("Positive update History")
    void updateHistoryShouldReturnOkStatus() throws Exception {
        UpdateHistoryRq updateHistoryRq = getUpdateHistoryRq();
        HistoryDto historyDto = new HistoryDto(getCreateHistoryRq().toEntity());
        historyDto.setId(ID);
        when(service.updateHistory(updateHistoryRq)).thenReturn(historyDto);
        mockMvc.perform(put("/api/history")
                        .content(new ObjectMapper().writeValueAsString(updateHistoryRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Negative update History (not found)")
    void updateHistoryShouldThrowException() throws Exception {
        UpdateHistoryRq updateHistoryRq = getUpdateHistoryRq();
        when(service.updateHistory(updateHistoryRq)).thenThrow(ResourceNotFoundException.class);
        mockMvc.perform(put("/api/history")
                        .content(new ObjectMapper().writeValueAsString(updateHistoryRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    @Test
    @DisplayName("Negative update History (failed validation)")
    void updateHistoryShouldThrowExceptionValidation() throws Exception {
        UpdateHistoryRq updateHistoryRq = getUpdateHistoryRq();
        updateHistoryRq.setName(null);
        HistoryDto historyDto = new HistoryDto(getCreateHistoryRq().toEntity());
        historyDto.setId(ID);
        when(service.updateHistory(updateHistoryRq)).thenReturn(historyDto);
        mockMvc.perform(put("/api/history")
                        .content(new ObjectMapper().writeValueAsString(updateHistoryRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(422));
    }

    @Test
    @DisplayName("Positive get History by Name")
    void getHistoryByNameShouldReturnOkStatus() throws Exception {
        List<HistoryDto> dtoList = List.of(new HistoryDto(getCreateHistoryRq().toEntity()),
                new HistoryDto(getCreateHistoryRq().toEntity()),
                new HistoryDto(getCreateHistoryRq().toEntity()));
        when(service.findHistoryByName(NAME, pageSize, pageNumber))
                .thenReturn(new PageDto<>(dtoList, pageNumber, pageSize, dtoList.size()));
        mockMvc.perform(get("/api/history/search?filter=" + NAME + "&pageNumber="
                        + pageNumber + "&pageSize=" + pageSize))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total", equalTo(3)));
    }

    @Test
    @DisplayName("Negative get History by Name (not found)")
    void getHistoryByNameShouldThrowException() throws Exception {
        when(service.findHistoryByName(NAME, pageSize, pageNumber))
                .thenThrow(ResourceNotFoundException.class);
        mockMvc.perform(get("/api/history/search?filter=" + NAME + "&pageNumber="
                        + pageNumber + "&pageSize=" + pageSize))
                .andExpect(status().is(404));
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
}