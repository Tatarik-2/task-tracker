package ewp.tasktracker.service.task;

import ewp.tasktracker.api.dto.page.PageDto;
import ewp.tasktracker.api.dto.task.CreateTaskRq;
import ewp.tasktracker.api.dto.task.TaskDto;
import ewp.tasktracker.api.dto.task.UpdateTaskRq;
import ewp.tasktracker.api.util.PageUtil;
import ewp.tasktracker.entity.TaskEntity;
import ewp.tasktracker.entity.common.Priority;
import ewp.tasktracker.entity.common.ProgressStatus;
import ewp.tasktracker.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {
    @MockBean
    private TaskRepository taskRepository;
    @MockBean
    private PageUtil pageUtil;
    @Mock
    private CreateTaskRq requestCreate;
    @Mock
    private UpdateTaskRq requestUpdate;

    @Autowired
    private TaskServiceImpl service;

    private static final String NAME = "Test name";
    private static final String DESCRIPTION = "Test description";
    private static final String HISTORY_ID = "Test historyId";
    private static final String AUTHOR_ID = "Test authorId";
    private static final String ASSIGNEE_ID = "Test assigneeId";
    private static final String ID = "12345";
    private static final int PAGE_NUMBER = 0;
    private static final int PAGE_SIZE = 20;
    private static final String NAME_2 = "NAME_2";


    @Test
    void saveTask() {
        TaskEntity testEntity = getTaskEntity();
        when(requestCreate.toEntity()).thenReturn(testEntity);
        when(taskRepository.save(any())).thenReturn(testEntity);

        service.saveTask(requestCreate);
        verify(taskRepository).save(testEntity);
    }

    @Test
    void findByIdTask() {
        when(taskRepository.findById(ID)).thenReturn(Optional.of(getTaskEntity()));
        TaskDto result = service.findById(ID);
        assertEquals(ID, result.getId());
    }

    @Test
    void findAllTask() {
        List<TaskEntity> listOfEntities = List.of(getTaskEntity(), getTaskEntity(), getTaskEntity());
        when(taskRepository.findAll(PageRequest.of(PAGE_NUMBER, PAGE_SIZE))).thenReturn(new PageImpl<>(listOfEntities));
        when(pageUtil.pageSizeControl(PAGE_SIZE)).thenReturn(PAGE_SIZE);
        List<TaskDto> dtoList = service.findAllTask(PAGE_SIZE, PAGE_NUMBER);
        assertEquals(3, dtoList.size());
    }

    @Test
    void updateTask() {
        TaskEntity entityFromDb = getTaskEntity();
        TaskEntity updatedEntity = getTaskEntity();
        updatedEntity.setName(NAME_2);
        when(requestUpdate.getId()).thenReturn(ID);
        when(taskRepository.findById(ID)).thenReturn(Optional.of(entityFromDb));
        when(taskRepository.save(any())).thenReturn(updatedEntity);
        TaskDto result = service.updateTask(requestUpdate);
        assertEquals(NAME_2, result.getName());
    }

    @Test
    void findTaskByName() {
        List<TaskEntity> listOfEntities = List.of(getTaskEntity(), getTaskEntity(), getTaskEntity());
        Page<TaskEntity> taskEntityPage = new PageImpl<>(listOfEntities,
                Pageable.ofSize(PAGE_SIZE).withPage(PAGE_NUMBER), listOfEntities.size());
        when(taskRepository.findByName(NAME.toUpperCase(),
                Pageable.ofSize(PAGE_SIZE).withPage(PAGE_NUMBER))).thenReturn(taskEntityPage);
        when(pageUtil.pageSizeControl(PAGE_SIZE)).thenReturn(PAGE_SIZE);
        PageDto<TaskDto> result = service.findTaskByName(NAME, PAGE_SIZE, PAGE_NUMBER);
        assertEquals(3, result.getTotal());
    }

    private TaskEntity getTaskEntity() {
        TaskEntity entity = new TaskEntity();
        entity.setName(NAME);
        entity.setDescription(DESCRIPTION);
        entity.setStatus(ProgressStatus.IN_PROGRESS);
        entity.setPriority(Priority.HIGH);
        entity.setHistoryId(HISTORY_ID);
        entity.setAuthorId(AUTHOR_ID);
        entity.setAssigneeId(ASSIGNEE_ID);
        entity.setId(ID);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }
}