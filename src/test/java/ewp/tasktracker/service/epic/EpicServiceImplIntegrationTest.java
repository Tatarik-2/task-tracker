package ewp.tasktracker.service.epic;

import ewp.tasktracker.api.dto.epic.CreateEpicRq;
import ewp.tasktracker.api.dto.epic.EpicDto;
import ewp.tasktracker.api.dto.epic.UpdateEpicRq;
import ewp.tasktracker.api.dto.page.PageDto;
import ewp.tasktracker.entity.EpicEntity;
import ewp.tasktracker.entity.common.Priority;
import ewp.tasktracker.entity.common.ProgressStatus;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.EpicRepository;
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
class EpicServiceImplIntegrationTest {

    @Autowired
    private EpicRepository epicRepository;

    @Autowired
    private EpicServiceImpl epicService;

    private static final String ID = "12345";
    private static final String NAME = "Test name";
    private static final String NAME_2 = "Test name another";
    private static final String DESCRIPTION = "Test description";
    private static final String PROJECT_ID = "Test projectId";
    private static final String AUTHOR_ID = "Test authorId";
    private static final String SUPERSPRINT_ID = "Test supersprintId";

    private static final int PAGE_NUMBER = 0;
    private static final int PAGE_SIZE = 20;

    @BeforeEach
    void init() {
        epicRepository.deleteAll();
        epicRepository.saveAll(getListEntities());
    }

    @AfterEach
    void cleanTable() {
        epicRepository.deleteAll();
    }


    @Test
    @DisplayName("Positive save Epic")
    void saveEpicShouldReturnEntity() {
        EpicDto dto = epicService.save(getCreateEpicRq());
        assertAll(
                () -> assertEquals(NAME, dto.getName()),
                () -> assertEquals(Priority.HIGH, dto.getPriority()),
                () -> assertEquals(ProgressStatus.IN_PROGRESS, dto.getStatus()),
                () -> assertEquals(DESCRIPTION, dto.getDescription()),
                () -> assertEquals(SUPERSPRINT_ID, dto.getSupersprintId()),
                () -> assertEquals(AUTHOR_ID, dto.getAuthorId()),
                () -> assertEquals(PROJECT_ID, dto.getProjectId())
        );
    }

    @Test
    @DisplayName("Positive update Epic")
    void updateEpicShouldReturnEntity() {
        UpdateEpicRq updateEpicRq = getUpdateEpicRq();
        updateEpicRq.setName(NAME_2);
        EpicDto result = epicService.update(updateEpicRq);
        assertEquals(NAME_2, result.getName());
    }

    @Test
    @DisplayName("Negative update Epic (not found)")
    void updateEpicShouldThrowException() {
        UpdateEpicRq updateEpicRq = getUpdateEpicRq();
        updateEpicRq.setId("1");
        assertThrows(ResourceNotFoundException.class, () -> epicService.update(updateEpicRq));
    }

    @Test
    @DisplayName("Positive find all Epics")
    void getListOfEpicsShouldReturnListOfEntities() {
        List<EpicDto> dtoList = epicService.getListOfEpics(PAGE_SIZE, PAGE_NUMBER);
        assertEquals(3, dtoList.size());
    }

    @Test
    @DisplayName("Positive find Epic by Id")
    void findEpicByIdShouldReturnEntity() {
        EpicDto dto  = epicService.findById(ID);
        assertEquals(ID, dto.getId());
    }

    @Test
    @DisplayName("Negative find Epic by Id (not found)")
    void findEpicByIdShouldThrowException() {
        assertThrows(ResourceNotFoundException.class, () -> epicService.findById(ID + "1"));
    }

    @Test
    @DisplayName("Positive find Epic by name")
    void findEpicByNameShouldReturnPageDto() {
        PageDto<EpicDto> pageDto = epicService.findByName(NAME, PAGE_SIZE, PAGE_NUMBER);
        assertEquals(3, pageDto.getTotal());
    }

    @Test
    @DisplayName("Positive find Epic by name (empty PageDto)")
    void findEpicByNameShouldReturnEmptyPageDto() {
        PageDto<EpicDto> pageDto = epicService.findByName(NAME_2, PAGE_SIZE, PAGE_NUMBER);
        assertEquals(0, pageDto.getTotal());
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
    private List<EpicEntity> getListEntities() {
        EpicEntity entity = getCreateEpicRq().toEntity();
        entity.setId(ID);
        return List.of(entity,
                getCreateEpicRq().toEntity(),
                getCreateEpicRq().toEntity());
    }
}