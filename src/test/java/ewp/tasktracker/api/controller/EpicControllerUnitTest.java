package ewp.tasktracker.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ewp.tasktracker.api.dto.epic.CreateEpicRq;
import ewp.tasktracker.api.dto.epic.EpicDto;
import ewp.tasktracker.api.dto.epic.UpdateEpicRq;
import ewp.tasktracker.api.dto.page.PageDto;
import ewp.tasktracker.entity.common.Priority;
import ewp.tasktracker.entity.common.ProgressStatus;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.service.epic.EpicServiceImpl;
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
class EpicControllerUnitTest {

    @MockBean
    private EpicServiceImpl service;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private static final String NAME = "Test name";
    private static final String DESCRIPTION = "Test description";
    private static final String PROJECT_ID = "Test projectId";
    private static final String AUTHOR_ID = "Test authorId";
    private static final String SUPERSPRINT_ID = "Test supersprintId";
    private static final String ID = "12345";
    private static final int PAGE_NUMBER = 0;
    private static final int PAGE_SIZE = 20;

    @Test
    @DisplayName("Positive get all Epics")
    void getListOfEpicsShouldReturnOkStatus() throws Exception {
        List<EpicDto> dtoList = getListDto();
        when(service.getListOfEpics(PAGE_SIZE, PAGE_NUMBER)).thenReturn(dtoList);
        mockMvc.perform(get("/api/epic?pageNumber=" + PAGE_NUMBER + "&pageSize=" + PAGE_SIZE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    @DisplayName("Positive get Epic by Id")
    void getEpicByIdShouldReturnOkStatus() throws Exception {
        CreateEpicRq createEpicRq = getCreateEpicRq();
        EpicDto epicDto = new EpicDto(createEpicRq.toEntity());
        epicDto.setId(ID);
        when(service.findById(ID)).thenReturn(epicDto);
        mockMvc.perform(get("/api/epic/" + ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(ID)));
    }

    @Test
    @DisplayName("Negative get Epic by Id (not found)")
    void getEpicByIdShouldThrowException() throws Exception {
        when(service.findById(ID)).thenThrow(ResourceNotFoundException.class);
        mockMvc.perform(get("/api/epic/" + ID))
                .andExpect(status().is(404));
    }

    @Test
    @DisplayName("Positive create epic")
    void createEpicShouldReturnOkStatus() throws Exception {
        CreateEpicRq createEpicRq = getCreateEpicRq();
        EpicDto dto = new EpicDto(createEpicRq.toEntity());
        when(service.save(createEpicRq)).thenReturn(dto);
        mockMvc.perform(post("/api/epic")
                        .content(new ObjectMapper().writeValueAsString(createEpicRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }

    @Test
    @DisplayName("Negative create Epic(failed validation)")
    void createEpicShouldThrowException() throws Exception {
        CreateEpicRq createEpicRq = getCreateEpicRq();
        createEpicRq.setName(null);
        EpicDto dto = new EpicDto(createEpicRq.toEntity());
        when(service.save(createEpicRq)).thenReturn(dto);
        mockMvc.perform(post("/api/epic")
                        .content(objectMapper.writeValueAsString(createEpicRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(422));
    }

    @Test
    @DisplayName("Positive update Epic")
    void updateEpicShouldReturnOkStatus() throws Exception {
        UpdateEpicRq updateEpicRq = getUpdateEpicRq();
        EpicDto epicDto = new EpicDto(getCreateEpicRq().toEntity());
        epicDto.setId(ID);
        when(service.update(updateEpicRq)).thenReturn(epicDto);
        mockMvc.perform(put("/api/epic")
                        .content(objectMapper.writeValueAsString(updateEpicRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Negative update epic (not found)")
    void updateEpicShouldThrowException() throws Exception {
        UpdateEpicRq updateEpicRq = getUpdateEpicRq();
        when(service.update(updateEpicRq)).thenThrow(ResourceNotFoundException.class);
        mockMvc.perform(put("/api/epic")
                        .content(objectMapper.writeValueAsString(updateEpicRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    @Test
    @DisplayName("Negative update epic (failed validation)")
    void updateEpicShouldThrowExceptionValidation() throws Exception {
        UpdateEpicRq updateEpicRq = getUpdateEpicRq();
        updateEpicRq.setName(null);
        EpicDto epicDto = new EpicDto(getCreateEpicRq().toEntity());
        epicDto.setId(ID);
        when(service.update(updateEpicRq)).thenReturn(epicDto);
        mockMvc.perform(put("/api/history")
                        .content(objectMapper.writeValueAsString(updateEpicRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(422));
    }

    @Test
    @DisplayName("Positive get epic by name")
    void getEpicByNameShouldReturnOkStatus() throws Exception {
        List<EpicDto> dtoList = getListDto();
        when(service.findByName(NAME, PAGE_SIZE, PAGE_NUMBER))
                .thenReturn(new PageDto<>(dtoList, PAGE_NUMBER, PAGE_SIZE, dtoList.size()));
        mockMvc.perform(get("/api/epic/search?filter=" + NAME + "&pageNumber="
                        + PAGE_NUMBER + "&pageSize=" + PAGE_SIZE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total", equalTo(3)));

    }

        @Test
        @DisplayName("Negative get epic by Name (empty PageDto)")
        void getEpicByNameShouldThrowException() throws Exception {
            when(service.findByName(NAME, PAGE_SIZE, PAGE_NUMBER))
                    .thenReturn(new PageDto<>(null, null, null, 0));
            mockMvc.perform(get("/api/epic/search?filter=" + NAME + "&pageNumber="
                            + PAGE_NUMBER + "&pageSize=" + PAGE_SIZE))
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
    private List<EpicDto> getListDto() {
        return List.of(new EpicDto(getCreateEpicRq().toEntity()),
                new EpicDto(getCreateEpicRq().toEntity()),
                new EpicDto(getCreateEpicRq().toEntity()));
    }
}