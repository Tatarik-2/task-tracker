package ewp.tasktracker.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ewp.tasktracker.api.dto.project.CreateProjectRq;
import ewp.tasktracker.api.dto.project.UpdateProjectRq;
import ewp.tasktracker.entity.ProjectEntity;
import ewp.tasktracker.entity.common.Status;
import ewp.tasktracker.repository.ProjectRepository;
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
class ProjectControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProjectRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String NAME = "Test name";
    private static final String NAME_SPACES = "  ";
    private static final String NAME_2 = "Test name another";
    private static final String DESCRIPTION = "Test description";
    private static final String AUTHORID = "X";
    private static final String WORKLOADID = "XII";
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
    @DisplayName("Positive get all Histories")
    void getAllHistoriesShouldReturnOkStatus() throws Exception {
        mockMvc.perform(get("/api/project?pageNumber=" + PAGE_NUMBER + "&pageSize=" + PAGE_SIZE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    @DisplayName("Positive get Project by Id")
    void getProjectByIdShouldReturnOkStatus() throws Exception {
        mockMvc.perform(get("/api/project/" + ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(ID)));
    }

    @Test
    @DisplayName("Negative get Project by Id (not found)")
    void getProjectByIdShouldThrowException() throws Exception {
        mockMvc.perform(get("/api/project/" + ID + "1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Positive create Project")
    void createProjectShouldReturnOkStatus() throws Exception {
        CreateProjectRq createProjectRq = getCreateProjectRq();
        createProjectRq.setName(NAME_2);
        mockMvc.perform(post("/api/project")
                        .content(objectMapper.writeValueAsString(createProjectRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME_2)));
    }

    @Test
    @DisplayName("Negative create Project (failed validation)")
    void createProjectShouldThrowException() throws Exception {
        CreateProjectRq createProjectRq = getCreateProjectRq();
        //createProjectRq.setName(null);
        createProjectRq.setName(NAME_SPACES);
        mockMvc.perform(post("/api/project/")
                        .content(objectMapper.writeValueAsString(createProjectRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(422));
    }

    @Test
    @DisplayName("Positive update Project")
    void updateProjectShouldReturnOkStatus() throws Exception {
        UpdateProjectRq updateProjectRq = getUpdateProjectRq();
        updateProjectRq.setName(NAME_2);
        mockMvc.perform(put("/api/project/")
                        .content(objectMapper.writeValueAsString(updateProjectRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME_2)));
    }

    @Test
    @DisplayName("Negative update Project (not found)")
    void updateProjectShouldThrowException() throws Exception {
        UpdateProjectRq updateProjectRq = getUpdateProjectRq();
        updateProjectRq.setId(ID + "1");
        mockMvc.perform(put("/api/project/")
                        .content(objectMapper.writeValueAsString(updateProjectRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Negative update project (failed validation)")
    void updateProjectShouldThrowExceptionValidation() throws Exception {
        UpdateProjectRq updateProjectRq = getUpdateProjectRq();
        //updateProjectRq.setName(null);
        updateProjectRq.setName(NAME_SPACES);
        mockMvc.perform(put("/api/project/")
                        .content(objectMapper.writeValueAsString(updateProjectRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(422));
    }


    private CreateProjectRq getCreateProjectRq() {
        CreateProjectRq createProjectRq = new CreateProjectRq();
        createProjectRq.setName(NAME);
        createProjectRq.setStatus(Status.ACTIVE);
        createProjectRq.setDescription(DESCRIPTION);
        createProjectRq.setAuthorId(AUTHORID);
        createProjectRq.setWorkloadId(WORKLOADID);
        return createProjectRq;
    }

    private UpdateProjectRq getUpdateProjectRq() {
        UpdateProjectRq projectRq = new UpdateProjectRq();
        projectRq.setId(ID);
        projectRq.setName(NAME);
        projectRq.setStatus(Status.ACTIVE);
        projectRq.setDescription(DESCRIPTION);
        projectRq.setAuthorId(AUTHORID);
        projectRq.setWorkloadId(WORKLOADID);
        return projectRq;
    }

    private List<ProjectEntity> getListEntities() {
        ProjectEntity entity = getCreateProjectRq().toEntity();
        entity.setId(ID);
        return List.of(entity,
                getCreateProjectRq().toEntity(),
                getCreateProjectRq().toEntity());
    }
}
