package ewp.tasktracker.api.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import ewp.tasktracker.api.dto.task.CreateTaskRq;
import ewp.tasktracker.api.dto.task.UpdateTaskRq;
import ewp.tasktracker.entity.TaskEntity;
import ewp.tasktracker.entity.common.Priority;
import ewp.tasktracker.entity.common.ProgressStatus;
import ewp.tasktracker.repository.TaskRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String NAME = "Test name";
    private static final String DESCRIPTION = "Test description";
    private static final String HISTORY_ID = "Test historyId";
    private static final String AUTHOR_ID = "Test authorId";
    private static final String ASSIGNEE_ID = "Test assigneeId";
    private static final String ID = "12345";
    private static final int PAGE_NUMBER = 0;
    private static final int PAGE_SIZE = 20;
    private static final String NAME_2 = "NAME_2";

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
    void getAllTask() throws Exception {
        mockMvc.perform(get("/api/task?pageNumber=" + PAGE_NUMBER + "&pageSize=" + PAGE_SIZE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void getTaskById() throws Exception {
        mockMvc.perform(get("/api/task/" + ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(ID)));
    }

    @Test
    void updateTask() throws Exception {
        UpdateTaskRq updateTaskRq = getUpdateTaskRq();
        updateTaskRq.setName(NAME_2);
        mockMvc.perform(put("/api/task")
                        .content(objectMapper.writeValueAsString(updateTaskRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME_2)));
    }

    @Test
    void createTask() throws Exception {
        CreateTaskRq createTaskRq = getCreateTaskRq();
        createTaskRq.setName(NAME_2);
        mockMvc.perform(post("/api/task")
                        .content(objectMapper.writeValueAsString(createTaskRq))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME_2)));
    }

    @Test
    void getTaskByName() throws Exception {
        mockMvc.perform(get("/api/task/search?filter=" + NAME + "&pageNumber="
                        + PAGE_NUMBER + "&pageSize=" + PAGE_SIZE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total", equalTo(3)));
    }

    private CreateTaskRq getCreateTaskRq() {
        CreateTaskRq createTaskRq = new CreateTaskRq();
        createTaskRq.setName(NAME);
        createTaskRq.setPriority(Priority.HIGH);
        createTaskRq.setStatus(ProgressStatus.IN_PROGRESS);
        createTaskRq.setDescription(DESCRIPTION);
        createTaskRq.setHistoryId(HISTORY_ID);
        createTaskRq.setAuthorId(AUTHOR_ID);
        createTaskRq.setAssigneeId(ASSIGNEE_ID);
        return createTaskRq;
    }

    private UpdateTaskRq getUpdateTaskRq() {
        UpdateTaskRq taskRq = new UpdateTaskRq();
        taskRq.setId(ID);
        taskRq.setName(NAME);
        taskRq.setPriority(Priority.HIGH);
        taskRq.setStatus(ProgressStatus.IN_PROGRESS);
        taskRq.setDescription(DESCRIPTION);
        taskRq.setHistoryId(HISTORY_ID);
        taskRq.setAuthorId(AUTHOR_ID);
        taskRq.setAssigneeId(ASSIGNEE_ID);
        return taskRq;
    }

    private List<TaskEntity> getListEntities() {
        TaskEntity entity = getCreateTaskRq().toEntity();
        entity.setId(ID);
        return List.of(entity,
                getCreateTaskRq().toEntity(),
                getCreateTaskRq().toEntity());
    }
}