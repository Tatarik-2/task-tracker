package ewp.tasktracker.service.history;

import ewp.tasktracker.api.dto.history.CreateHistoryRq;
import ewp.tasktracker.api.dto.history.HistoryDto;
import ewp.tasktracker.api.dto.history.UpdateHistoryRq;
import ewp.tasktracker.api.dto.page.PageDto;
import ewp.tasktracker.api.util.PageUtil;
import ewp.tasktracker.entity.HistoryEntity;
import ewp.tasktracker.entity.common.Priority;
import ewp.tasktracker.entity.common.ProgressStatus;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.HistoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class HistoryServiceImplUnitTest {

    @MockBean
    private HistoryRepository historyRepository;
    @MockBean
    private PageUtil pageUtil;
    @Mock
    private CreateHistoryRq requestCreate;
    @Mock
    private UpdateHistoryRq requestUpdate;

    @Autowired
    private HistoryServiceImpl service;

    private static final String NAME = "Test name";
    private static final String DESCRIPTION = "Test description";
    private static final String EPIC_ID = "Test epicId";
    private static final String AUTHOR_ID = "Test authorId";
    private static final String SPRINT_ID = "Test sprintId";
    private static final String ID = "12345";
    private static final String TEST_NAME_UPDATED = "New test name";
    private static final int pageNumber = 0;
    private static final int pageSize = 20;

    @Test
    @DisplayName("Positive create History")
    void saveHistoryShouldReturnEntity() {
        HistoryEntity testEntity = getHistoryEntity();
        when(requestCreate.toEntity()).thenReturn(testEntity);
        when(historyRepository.save(any())).thenReturn(testEntity);
        HistoryDto result = service.saveHistory(requestCreate);
        Assertions.assertAll(
                () -> assertEquals(HistoryDto.class, result.getClass()),
                () -> assertEquals(NAME, result.getName()),
                () -> assertEquals(DESCRIPTION, result.getDescription()),
                () -> assertEquals(ProgressStatus.IN_PROGRESS, result.getStatus()),
                () -> assertEquals(Priority.HIGH, result.getPriority()),
                () -> assertEquals(EPIC_ID, result.getEpicId()),
                () -> assertEquals(AUTHOR_ID, result.getAuthorId()),
                () -> assertEquals(SPRINT_ID, result.getSprintId()),
                () -> assertEquals(ID, result.getId())
        );
    }

    @Test
    @DisplayName("Positive findById History")
    void findByIdHistoryShouldReturnEntity() {
        when(historyRepository.findById(ID)).thenReturn(Optional.of(getHistoryEntity()));
        HistoryDto result = service.findHistoryById(ID);
        assertEquals(ID, result.getId());
    }

    @Test
    @DisplayName("Negative findById History")
    void findByIdHistoryShouldThrowException() {
        when(historyRepository.findById(ID)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.findHistoryById(ID));
    }

    @Test
    @DisplayName("Positive show all Histories(pagination)")
    void findAllHistoriesShouldReturnListOfEntities() {
        List<HistoryEntity> listOfEntities = List.of(getHistoryEntity(), getHistoryEntity(), getHistoryEntity());
        when(historyRepository.findAll(PageRequest.of(pageNumber, pageSize))).thenReturn(new PageImpl<>(listOfEntities));
        when(pageUtil.pageSizeControl(pageSize)).thenReturn(pageSize);
        List<HistoryDto> dtoList = service.findAllHistories(pageSize, pageNumber);
        assertEquals(3, dtoList.size());
    }

    @Test
    @DisplayName("Positive update History")
    void updateHistoryShouldReturnEntity() {
        HistoryEntity historyEntityFromDB = getHistoryEntity();
        HistoryEntity updatedEntity = getHistoryEntity();
        updatedEntity.setName(TEST_NAME_UPDATED);
        when(requestUpdate.getId()).thenReturn(ID);
        when(historyRepository.findById(ID)).thenReturn(Optional.of(historyEntityFromDB));
        when(historyRepository.save(any())).thenReturn(updatedEntity);
        HistoryDto result = service.updateHistory(requestUpdate);
        assertEquals(TEST_NAME_UPDATED, result.getName());
    }

    @Test
    @DisplayName("Negative update History")
    void updateHistoryShouldThrowException() {
        when(historyRepository.findById(ID)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.updateHistory(requestUpdate));
    }

    @Test
    @DisplayName("Positive findByName History")
    void findHistoryByNameShouldReturnPageDto() {
        List<HistoryEntity> listOfEntities = List.of(getHistoryEntity(), getHistoryEntity(), getHistoryEntity());
        when(historyRepository.findByName(NAME)).thenReturn(listOfEntities);
        when(pageUtil.pageSizeControl(pageSize)).thenReturn(pageSize);
        PageDto<HistoryDto> result = service.findHistoryByName(NAME, pageSize, pageNumber);
        assertEquals(3, result.getTotal());
    }

    private HistoryEntity getHistoryEntity() {
        HistoryEntity entity = new HistoryEntity();
        entity.setName(NAME);
        entity.setDescription(DESCRIPTION);
        entity.setStatus(ProgressStatus.IN_PROGRESS);
        entity.setPriority(Priority.HIGH);
        entity.setEpicId(EPIC_ID);
        entity.setAuthorId(AUTHOR_ID);
        entity.setSprintId(SPRINT_ID);
        entity.setId(ID);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }
}