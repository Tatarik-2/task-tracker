package ewp.tasktracker.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ewp.tasktracker.api.dto.project.CreateProjectRq;
import ewp.tasktracker.api.dto.project.ProjectDto;
import ewp.tasktracker.api.dto.project.UpdateProjectRq;
import ewp.tasktracker.entity.common.Status;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.service.project.ProjectServiceImpl;
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
@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProjectServiceImpl service;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String NAME = "Test name";
    private static final String ID = "12345._qdqwqq4548";
    private static final String NEWID = "12345.;,we[fgwmegm[/.";
    private static final String DESC = "DescTest";
    private static final String AUTHORID = "X";
    private static final String WORKLOADID = "XII";
    private static final String NEWWORKLOADID = "XVVVVII";
    private static final String TEST_NAME_UPDATED = "New test name";
    private static final int PAGE_NUMBER = 0;
    private static final int PAGE_SIZE = 20;


    @Test
    @DisplayName("Positive get all Projects")
    void getAllProjectsShouldReturnOkStatus() throws Exception {
        List<ProjectDto> dtoList = getListDto();
        when(service.findAll(PAGE_SIZE, PAGE_NUMBER)).thenReturn(dtoList);
        mockMvc.perform(get("/api/project?pageNumber=" + PAGE_NUMBER + "&pageSize=" + PAGE_SIZE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    @DisplayName("Positive get Project by Id")
    void getProjectByIdShouldReturnOkStatus() throws Exception {
        CreateProjectRq createProjectRq = getCreateProjectRq();
        ProjectDto projectDto = new ProjectDto(createProjectRq.toEntity());
        projectDto.setId(ID);
        when(service.findById(ID)).thenReturn(projectDto);
        mockMvc.perform(get("/api/project/" + ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(ID)));
    }

    @Test
    @DisplayName("Negative get Project by Id (not found)")
    void getProjectByIdShouldThrowException() throws Exception {
        when(service.findById(ID)).thenThrow(ResourceNotFoundException.class);
        mockMvc.perform(get("/api/project/" + ID))
                .andExpect(status().is(404));
    }

    @Test
    @DisplayName("Positive create Project")
    void createProjectShouldReturnOkStatus() throws Exception {
        CreateProjectRq createProjectRq = getCreateProjectRq();
        ProjectDto dto = new ProjectDto(createProjectRq.toEntity());
        when(service.create(createProjectRq)).thenReturn(dto);
        mockMvc.perform(post("/api/project/")
                        .content(new ObjectMapper().writeValueAsString(createProjectRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME)));
    }

    @Test
    @DisplayName("Negative create Project(failed validation)")
    void createProjectShouldThrowException() throws Exception {
        CreateProjectRq createProjectRq = getCreateProjectRq();
        createProjectRq.setName(null);
        ProjectDto dto = new ProjectDto(createProjectRq.toEntity());
        when(service.create(createProjectRq)).thenReturn(dto);
        mockMvc.perform(post("/api/project/")
                        .content(objectMapper.writeValueAsString(createProjectRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(422));
    }

    @Test
    @DisplayName("Positive update Project")
    void updateProjectShouldReturnOkStatus() throws Exception {
        UpdateProjectRq updateProjectRq = getUpdateProjectRq();
        ProjectDto projectDto = new ProjectDto(getCreateProjectRq().toEntity());
        projectDto.setId(ID);
        when(service.updateProject(updateProjectRq)).thenReturn(projectDto);
        mockMvc.perform(put("/api/project/")
                        .content(objectMapper.writeValueAsString(updateProjectRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Negative update Project (not found)")
    void updateProjectShouldThrowException() throws Exception {
        UpdateProjectRq updateProjectRq = getUpdateProjectRq();
        when(service.updateProject(updateProjectRq)).thenThrow(ResourceNotFoundException.class);
        mockMvc.perform(put("/api/project/")
                        .content(objectMapper.writeValueAsString(updateProjectRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }

    @Test
    @DisplayName("Negative update Project (failed validation)")
    void updateProjectShouldThrowExceptionValidation() throws Exception {
        UpdateProjectRq updateProjectRq = getUpdateProjectRq();
        updateProjectRq.setName(null);
        ProjectDto projectDto = new ProjectDto(getCreateProjectRq().toEntity());
        projectDto.setId(ID);
        when(service.updateProject(updateProjectRq)).thenReturn(projectDto);
        mockMvc.perform(put("/api/project/")
                        .content(objectMapper.writeValueAsString(updateProjectRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(422));
    }

    private CreateProjectRq getCreateProjectRq() {
        CreateProjectRq createProjectRq = new CreateProjectRq();
        createProjectRq.setName(NAME);
        createProjectRq.setStatus(Status.INACTIVE);
        createProjectRq.setDescription(DESC);
        createProjectRq.setAuthorId(AUTHORID);
        createProjectRq.setWorkloadId(WORKLOADID);
        return createProjectRq;
    }

    private UpdateProjectRq getUpdateProjectRq() {
        UpdateProjectRq projectRq = new UpdateProjectRq();
        projectRq.setId(NEWID);
        projectRq.setName(TEST_NAME_UPDATED);
        projectRq.setStatus(Status.ACTIVE);
        projectRq.setDescription(DESC);
        projectRq.setAuthorId(AUTHORID);
        projectRq.setWorkloadId(NEWWORKLOADID);
        return projectRq;
    }

    private List<ProjectDto> getListDto() {
        return List.of(new ProjectDto(getCreateProjectRq().toEntity()),
                new ProjectDto(getCreateProjectRq().toEntity()),
                new ProjectDto(getCreateProjectRq().toEntity()));
    }
}
