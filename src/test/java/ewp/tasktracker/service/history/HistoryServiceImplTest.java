package ewp.tasktracker.service.history;

import ewp.tasktracker.api.dto.history.CreateHistoryRq;
import ewp.tasktracker.api.dto.history.HistoryDto;
import ewp.tasktracker.api.util.PageUtil;
import ewp.tasktracker.entity.HistoryEntity;
import ewp.tasktracker.entity.common.Priority;
import ewp.tasktracker.entity.common.ProgressStatus;
import ewp.tasktracker.repository.HistoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.UUID;



import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class HistoryServiceImplTest {

    @InjectMocks
    private HistoryServiceImpl historyService;
    @Mock
    private HistoryRepository historyRepository;

    @Test
    @DisplayName("Positive create History")
    void normalSaveHistory() {
        CreateHistoryRq createHistoryRq = getCrHistory();
        HistoryEntity entityToSave = createHistoryRq.toEntity();
        //HistoryEntity entity = getHistoryEntity();
        HistoryDto dto = new HistoryDto(entityToSave);
        //doReturn(entityToSave).when(historyRepository.save(entityToSave));
        Mockito.when(historyRepository.save(entityToSave)).thenReturn(entityToSave);
        HistoryDto result = historyService.saveHistory(createHistoryRq);
        Assertions.assertAll(
                () -> assertEquals(result.getClass(), HistoryDto.class),
                () -> assertEquals(result.getName(), createHistoryRq.getName()),
                () -> assertEquals(result.getDescription(), createHistoryRq.getDescription()),
                () -> assertEquals(result.getStatus(), createHistoryRq.getStatus()),
                () -> assertEquals(result.getPriority(), createHistoryRq.getPriority()),
                () -> assertEquals(result.getEpicId(), createHistoryRq.getEpicId()),
                () -> assertEquals(result.getAuthorId(), createHistoryRq.getAuthorId()),
                () -> assertEquals(result.getSprintId(), createHistoryRq.getSprintId())

        );


    }

    private CreateHistoryRq getCrHistory() {
        CreateHistoryRq createHistoryRq = new CreateHistoryRq();
        createHistoryRq.setName("name");
        createHistoryRq.setDescription("description");
        createHistoryRq.setStatus(ProgressStatus.IN_PROGRESS);
        createHistoryRq.setPriority(Priority.HIGH);
        createHistoryRq.setEpicId("epicId");
        createHistoryRq.setAuthorId("authorId");
        createHistoryRq.setSprintId("sprintId");
        return createHistoryRq;
    }

    private HistoryEntity getHistoryEntity() {
        HistoryEntity entity = new HistoryEntity();
        entity.setName("name");
        entity.setDescription("description");
        entity.setStatus(ProgressStatus.IN_PROGRESS);
        entity.setPriority(Priority.HIGH);
        entity.setEpicId("epicId");
        entity.setAuthorId("authorId");
        entity.setSprintId("sprintId");
        entity.setId(UUID.randomUUID().toString());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setJustCreated(true);
        return entity;
    }
}