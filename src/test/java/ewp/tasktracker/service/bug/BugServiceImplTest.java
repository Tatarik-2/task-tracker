package ewp.tasktracker.service.bug;

import ewp.tasktracker.api.dto.bug.BugDto;
import ewp.tasktracker.api.dto.bug.CreateBugRq;
import ewp.tasktracker.api.dto.bug.UpdateBugRq;
import ewp.tasktracker.api.dto.page.PageDto;
import ewp.tasktracker.api.util.PageUtil;
import ewp.tasktracker.entity.BugEntity;
import ewp.tasktracker.entity.common.Priority;
import ewp.tasktracker.entity.common.ProgressStatus;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.BugRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BugServiceImplTest {
    @MockBean
    private BugRepository bugRepository;
    @MockBean
    private PageUtil pageUtil;
    @Mock
    private CreateBugRq requestCreate;
    @Mock
    private UpdateBugRq requestUpdate;
    @Autowired
    private BugServiceImpl service;

    private static final String NAME = "Test name";
    private static final String DESCRIPTION = "Test description";
    private static final String HISTORY_ID = "Test history";
    private static final String AUTHOR_ID = "Test authorId";
    private static final String ASSIGNEE_ID = "Test assigneeId";
    private static final String SPRINT_ID = "Test sprintId";
    private static final String ID = "12345";
    private static final String TEST_NAME_UPDATED = "New test name";
    private static final int PAGE_NUMBER = 0;
    private static final int PAGE_SIZE = 20;

    @Test
    @DisplayName("Positive create bug")
    void createShouldReturnEntity() {
        BugEntity entity = getBugEntity();
        when(requestCreate.toEntity()).thenReturn(entity);
        when(bugRepository.save(any())).thenReturn(entity);
        BugDto result = service.create(requestCreate);
        Assertions.assertAll(
                () -> assertEquals(BugDto.class, result.getClass()),
                () -> assertEquals(NAME, result.getName()),
                () -> assertEquals(DESCRIPTION, result.getDescription()),
                () -> assertEquals(ProgressStatus.IN_PROGRESS, result.getStatus()),
                () -> assertEquals(Priority.HIGH, result.getPriority()),
                () -> assertEquals(HISTORY_ID, result.getHistoryId()),
                () -> assertEquals(AUTHOR_ID, result.getAuthorId()),
                () -> assertEquals(ASSIGNEE_ID, result.getAssigneeId()),
                () -> assertEquals(SPRINT_ID, result.getSprintId()),
                () -> assertEquals(ID, result.getId())
        );

    }

    @Test
    @DisplayName("Positive findById Bug")
    void findByIdShouldReturnEntity() {
        when(bugRepository.findById(ID)).thenReturn(Optional.of(getBugEntity()));
        BugDto result = service.findById(ID);
        assertEquals(ID, result.getId());
    }

    @Test
    @DisplayName("Negative findById Bug")
    void findByIdShouldThrowException() {
        when(bugRepository.findById(ID)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.findById(ID));
    }

    @Test
    @DisplayName("Positive findByName Bug")
    void findByNameShouldReturnPageDto() {
        List<BugEntity> listOfEntities = List.of(getBugEntity(), getBugEntity(), getBugEntity());
        Page<BugEntity> bugEntityPage = new PageImpl<>(listOfEntities,
                Pageable.ofSize(PAGE_SIZE).withPage(PAGE_NUMBER), listOfEntities.size());
        when(bugRepository.findByName(NAME.toUpperCase(),
                Pageable.ofSize(PAGE_SIZE).withPage(PAGE_NUMBER))).thenReturn(bugEntityPage);
        when(pageUtil.pageSizeControl(PAGE_SIZE)).thenReturn(PAGE_SIZE);
        PageDto<BugDto> result = service.findByName(NAME, PAGE_SIZE, PAGE_NUMBER);
        assertEquals(3, result.getTotal());
    }

    @Test
    @DisplayName("Positive findAll Bug")
    void findAllShouldReturnListOfEntities() {
        List<BugEntity> listOfEntities = List.of(getBugEntity(), getBugEntity(), getBugEntity());
        when(bugRepository.findAll(PageRequest.of(PAGE_NUMBER, PAGE_SIZE))).thenReturn(new PageImpl<>(listOfEntities));
        when(pageUtil.pageSizeControl(PAGE_SIZE)).thenReturn(PAGE_SIZE);
        List<BugDto> dtoList = service.findAll(PAGE_SIZE, PAGE_NUMBER);
        assertEquals(3, dtoList.size());
    }

    @Test
    @DisplayName("Positive update Bug")
    void updateShouldReturnEntity() {
        BugEntity bugEntityFromBD = getBugEntity();
        BugEntity updatedEntity = getBugEntity();
        updatedEntity.setName(TEST_NAME_UPDATED);
        when(requestUpdate.getId()).thenReturn(ID);
        when(bugRepository.findById(ID)).thenReturn(Optional.of(bugEntityFromBD));
        when(bugRepository.save(any())).thenReturn(updatedEntity);
        BugDto result = service.update(requestUpdate);
        assertEquals(TEST_NAME_UPDATED, result.getName());
    }

    @Test
    @DisplayName("Negative update Bug")
    void updateShouldThrowException() {
        when(bugRepository.findById(ID)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.update(requestUpdate));

    }

    private BugEntity getBugEntity() {
        BugEntity entity = new BugEntity();
        entity.setName(NAME);
        entity.setDescription(DESCRIPTION);
        entity.setStatus(ProgressStatus.IN_PROGRESS);
        entity.setPriority(Priority.HIGH);
        entity.setHistoryId(HISTORY_ID);
        entity.setAssigneeId(ASSIGNEE_ID);
        entity.setAuthorId(AUTHOR_ID);
        entity.setSprintId(SPRINT_ID);
        entity.setId(ID);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }
}