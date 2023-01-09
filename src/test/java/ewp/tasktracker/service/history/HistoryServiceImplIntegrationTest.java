package ewp.tasktracker.service.history;

import ewp.tasktracker.api.dto.history.CreateHistoryRq;
import ewp.tasktracker.api.dto.history.HistoryDto;
import ewp.tasktracker.api.dto.history.UpdateHistoryRq;
import ewp.tasktracker.api.dto.page.PageDto;
import ewp.tasktracker.entity.HistoryEntity;
import ewp.tasktracker.entity.common.Priority;
import ewp.tasktracker.entity.common.ProgressStatus;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.HistoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class HistoryServiceImplIntegrationTest {

    @Autowired
    private HistoryRepository repository;

    @Autowired
    private HistoryServiceImpl service;

    private static final String NAME = "Test name";
    private static final String NAME_2 = "Test name another";
    private static final String DESCRIPTION = "Test description";
    private static final String EPIC_ID = "Test epicId";
    private static final String AUTHOR_ID = "Test authorId";
    private static final String SPRINT_ID = "Test sprintId";
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
    @DisplayName("Positive save History")
    void saveHistoryShouldReturnEntity() {
        HistoryDto dto = service.saveHistory(getCreateHistoryRq());
        assertAll(
                () -> assertEquals(NAME, dto.getName()),
                () -> assertEquals(Priority.HIGH, dto.getPriority()),
                () -> assertEquals(ProgressStatus.IN_PROGRESS, dto.getStatus()),
                () -> assertEquals(DESCRIPTION, dto.getDescription()),
                () -> assertEquals(SPRINT_ID, dto.getSprintId()),
                () -> assertEquals(AUTHOR_ID, dto.getAuthorId()),
                () -> assertEquals(EPIC_ID, dto.getEpicId())
        );
    }

    @Test
    @DisplayName("Positive find History by Id")
    void findHistoryByIdShouldReturnEntity() {
        HistoryDto dto = service.findHistoryById(ID);
        assertEquals(ID, dto.getId());
    }

    @Test
    @DisplayName("Negative find History by Id (not found)")
    void findHistoryByIdShouldThrowException() {
        assertThrows(ResourceNotFoundException.class, () -> service.findHistoryById(ID + "1"));
    }

    @Test
    @DisplayName("Positive find all Histories")
    void findAllHistoriesShouldReturnListOfEntities() {
        List<HistoryDto> dtoList = service.findAllHistories(PAGE_SIZE, PAGE_NUMBER);
        assertEquals(3, dtoList.size());
    }

    @Test
    @DisplayName("Positive update History")
    void updateHistoryShouldReturnEntity() {
        UpdateHistoryRq updateHistoryRq = getUpdateHistoryRq();
        updateHistoryRq.setName(NAME_2);
        HistoryDto result = service.updateHistory(updateHistoryRq);
        assertEquals(NAME_2, result.getName());
    }

    @Test
    @DisplayName("Negative update History (not found)")
    void updateHistoryShouldThrowException() {
        UpdateHistoryRq updateHistoryRq = getUpdateHistoryRq();
        updateHistoryRq.setId("1");
        assertThrows(ResourceNotFoundException.class, () -> service.updateHistory(updateHistoryRq));
    }

    @Test
    @DisplayName("Positive find History by name")
    void findHistoryByNameShouldReturnPageDto() {
        PageDto<HistoryDto> pageDto = service.findHistoryByName(NAME, PAGE_SIZE, PAGE_NUMBER);
        assertEquals(3, pageDto.getTotal());
    }

    @Test
    @DisplayName("Positive find History by name (empty PageDto)")
    void findHistoryByNameShouldReturnEmptyPageDto() {
        PageDto<HistoryDto> pageDto = service.findHistoryByName(NAME_2, PAGE_SIZE, PAGE_NUMBER);
        assertEquals(0, pageDto.getTotal());
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

    private List<HistoryEntity> getListEntities() {
        HistoryEntity entity = getCreateHistoryRq().toEntity();
        entity.setId(ID);
        return List.of(entity,
                getCreateHistoryRq().toEntity(),
                getCreateHistoryRq().toEntity());
    }
}