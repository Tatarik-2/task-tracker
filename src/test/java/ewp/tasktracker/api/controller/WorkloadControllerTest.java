package ewp.tasktracker.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ewp.tasktracker.api.dto.workload.CreateWorkloadRq;
import ewp.tasktracker.api.dto.workload.UpdateWorkloadRq;
import ewp.tasktracker.entity.WorkloadEntity;
import ewp.tasktracker.entity.common.Status;
import ewp.tasktracker.repository.WorkloadRepository;
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
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class WorkloadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WorkloadRepository workloadRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String NAME = "Test name";
    private static final String NOT_VALID_NAME = "T";
    private static final String UPDATED_NAME = "New test name";
    private static final String AUTHOR_ID = "Author ID";
    private static final String NON_EXISTENT_ID = "123456789123456789123456789123456789";
    private static final int TEST_TABLE_SIZE = 5;
    private static final int PAGE_NUMBER = 0;
    private static final int PAGE_SIZE = 10;

    @BeforeEach
    public void cleanTable() {
        workloadRepository.deleteAll();
    }

    @Test
    @DisplayName("The method getAll() in the WorkloadController works correctly")
    public void getAllShouldReturnAllEntities() throws Exception {
        List<WorkloadEntity> savedWorkloads = new ArrayList<>();
        for (int i = 0; i < TEST_TABLE_SIZE; i++) {
            CreateWorkloadRq createWorkloadRq = CreateWorkloadRq.builder()
                    .name(NAME + i)
                    .status(Status.ACTIVE)
                    .authorId(AUTHOR_ID + i)
                    .build();
            savedWorkloads.add(workloadRepository.save(createWorkloadRq.toEntity()));
        }

        ResultActions response = mockMvc.perform(get("/api/workload?pageNumber={pageNumber}&pageSize={pageSize}",
                PAGE_NUMBER, PAGE_SIZE));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(savedWorkloads.size())))
                .andExpect(jsonPath("$[0].name").
                        value(savedWorkloads.get(0).getName()))
                .andExpect(jsonPath("$[2].authorId").
                        value(savedWorkloads.get(2).getAuthorId()))
                .andExpect(jsonPath("$[4].status").
                        value(savedWorkloads.get(4).getStatus().toString()));
//


    }

    @Test
    @DisplayName("The method getById() in the WorkloadController works correctly")
    public void getByIdShouldReturnTrueEntity() throws Exception {
        CreateWorkloadRq workloadRq = prepareCreateWorkloadRq();//создаем объект CreateWorkloadRq
        WorkloadEntity workloadEntity = workloadRepository.save(workloadRq.toEntity());//сохраняем его в БД

        ResultActions response = mockMvc.perform(get("/api/workload/{id}", workloadEntity.getId()));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").
                        value(workloadEntity.getName()))
                .andExpect(jsonPath("$.status",
                        is(workloadEntity.getStatus().toString())))
                .andExpect(jsonPath("$.authorId",
                        is(workloadEntity.getAuthorId())));
    }

    @Test
    @DisplayName("The method getById() in the WorkloadController throw 404 error")
    public void getByIdShouldReturn404Error() throws Exception {

        ResultActions response = mockMvc.perform(get("/api/workload/{id}", NON_EXISTENT_ID));

        response
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("The method create() in the WorkloadController works correctly")
    public void createShouldReturnCreatedEntity() throws Exception {

        CreateWorkloadRq workloadRq = prepareCreateWorkloadRq();

        ResultActions response = mockMvc.perform(post("/api/workload")
                .content(objectMapper.writeValueAsString(workloadRq))
                .contentType(MediaType.APPLICATION_JSON));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").
                        value(workloadRq.getName()))
                .andExpect(jsonPath("$.status",
                        is(workloadRq.getStatus().toString())))
                .andExpect(jsonPath("$.authorId",
                        is(workloadRq.getAuthorId())));
    }

    @Test
    @DisplayName("The method create() in the WorkloadController throw 422 error")
    public void createShouldReturn422ErrorAtCreate() throws Exception {

        CreateWorkloadRq workloadRq = prepareCreateWorkloadRq();
        workloadRq.setName(NOT_VALID_NAME);

        ResultActions response = mockMvc.perform(post("/api/workload")
                .content(objectMapper.writeValueAsString(workloadRq))
                .contentType(MediaType.APPLICATION_JSON));

        response.andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @DisplayName("The method update() in the WorkloadController works correctly")
    public void updateShouldReturnUpdatedEntity() throws Exception {

        CreateWorkloadRq workloadRq = prepareCreateWorkloadRq();//создаем объект CreateWorkloadRq
        WorkloadEntity workloadEntity = workloadRepository.save(workloadRq.toEntity());//сохраняем его в БД
        UpdateWorkloadRq updateWorkloadRq = prepareUpdateWorkloadRq();//создаем обновленный UpdateWorkloadRq
        updateWorkloadRq.setId(workloadEntity.getId());//устанавливаем требуемый ID

        ResultActions response = mockMvc.perform(put("/api/workload")
                .content(objectMapper.writeValueAsString(updateWorkloadRq))
                .contentType(MediaType.APPLICATION_JSON));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").
                        value(updateWorkloadRq.getName()));
    }

    @Test
    @DisplayName("The method update() in the WorkloadController throw 404 error")
    public void updateShouldReturn404ErrorAtUpdate() throws Exception {
        UpdateWorkloadRq updateWorkloadRq = prepareUpdateWorkloadRq();//создаем UpdateWorkloadRq
        updateWorkloadRq.setId(NON_EXISTENT_ID);//устанавливаем требуемый несуществующий ID

        ResultActions response = mockMvc.perform(put("/api/workload")
                .content(objectMapper.writeValueAsString(updateWorkloadRq))
                .contentType(MediaType.APPLICATION_JSON));

        response.andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("The method update() in the WorkloadController throw 422 error")
    public void updateShouldReturn422ErrorAtUpdate() throws Exception {
        CreateWorkloadRq workloadRq = prepareCreateWorkloadRq();//создаем объект CreateWorkloadRq
        WorkloadEntity workloadEntity = workloadRepository.save(workloadRq.toEntity());//сохраняем его в БД
        UpdateWorkloadRq updateWorkloadRq = prepareUpdateWorkloadRq();//создаем обновленный UpdateWorkloadRq
        updateWorkloadRq.setId(workloadEntity.getId());//устанавливаем требуемый ID
        updateWorkloadRq.setName(NOT_VALID_NAME);//устанавливаем невалидный Name

        ResultActions response = mockMvc.perform(put("/api/workload")
                .content(objectMapper.writeValueAsString(updateWorkloadRq))
                .contentType(MediaType.APPLICATION_JSON));

        response.andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }


    private CreateWorkloadRq prepareCreateWorkloadRq() {
        CreateWorkloadRq workloadRq = CreateWorkloadRq.builder()
                .name(NAME)
                .status(Status.ACTIVE)
                .authorId(AUTHOR_ID)
                .build();
        return workloadRq;
    }

    private UpdateWorkloadRq prepareUpdateWorkloadRq() {
        UpdateWorkloadRq workloadRq = UpdateWorkloadRq.builder()
                .name(UPDATED_NAME)
                .status(Status.ACTIVE)
                .authorId(AUTHOR_ID)
                .build();
        return workloadRq;
    }


}
