package ewp.tasktracker.service.epic;

import ewp.tasktracker.api.dto.epic.CreateEpicRq;
import ewp.tasktracker.api.dto.epic.EpicDto;
import ewp.tasktracker.api.dto.epic.UpdateEpicRq;
import ewp.tasktracker.api.dto.history.HistoryDto;
import ewp.tasktracker.api.dto.page.PageDto;
import ewp.tasktracker.api.util.PageUtil;
import ewp.tasktracker.entity.EpicEntity;
import ewp.tasktracker.entity.common.Priority;
import ewp.tasktracker.entity.common.ProgressStatus;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.EpicRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EpicServiceImplUnitTest {

    @MockBean
    private EpicRepository epicRepository;

    @MockBean
    private PageUtil pageUtil;

    @Mock
    private CreateEpicRq requestCreate;

    @Mock
    private UpdateEpicRq requestUpdate;

    @Autowired
    private EpicServiceImpl epicService;

    private static final String NAME = "Test name";
    private static final String DESCRIPTION = "Test description";
    private static final String PROJECT_ID = "Test projectId";
    private static final String AUTHOR_ID = "Test authorId";
    private static final String SUPERSPRINT_ID = "Test supersprintId";
    private static final String ID = "12345";
    private static final String TEST_NAME_UPDATED = "New test name";
    private static final int PAGE_NUMBER = 0;
    private static final int PAGE_SIZE = 20;

    private EpicEntity getEpicEntity() {
        EpicEntity epicEntity = new EpicEntity();
        epicEntity.setId(ID);
        epicEntity.setName(NAME);
        epicEntity.setDescription(DESCRIPTION);
        epicEntity.setStatus(ProgressStatus.TODO);
        epicEntity.setPriority(Priority.LOW);
        epicEntity.setProjectId(PROJECT_ID);
        epicEntity.setAuthorId(AUTHOR_ID);
        epicEntity.setSupersprintId(SUPERSPRINT_ID);
        epicEntity.setCreatedAt(LocalDateTime.now());
        epicEntity.setUpdatedAt(LocalDateTime.now());
        return epicEntity;
    }

    @Test
    @DisplayName("Positive create Epic")
    void saveEpicShouldReturnEntity() {
        EpicEntity testEntity = getEpicEntity();
        when(requestCreate.toEntity()).thenReturn(testEntity);
        when(epicRepository.save(any())).thenReturn(testEntity);
        EpicDto result = epicService.save(requestCreate);
        Assertions.assertAll(
                () -> assertEquals(HistoryDto.class, result.getClass()),
                () -> assertEquals(NAME, result.getName()),
                () -> assertEquals(DESCRIPTION, result.getDescription()),
                () -> assertEquals(ProgressStatus.TODO, result.getStatus()),
                () -> assertEquals(Priority.LOW, result.getPriority()),
                () -> assertEquals(PROJECT_ID, result.getProjectId()),
                () -> assertEquals(AUTHOR_ID, result.getAuthorId()),
                () -> assertEquals(SUPERSPRINT_ID, result.getSupersprintId()),
                () -> assertEquals(ID, result.getId())
        );

    }

    @Test
    @DisplayName("Positive update Epic")
    void updateEpicShouldReturnEntity() {
        EpicEntity epicEntityFromDB = getEpicEntity();
        EpicEntity updatedEntity = getEpicEntity();
        updatedEntity.setName(TEST_NAME_UPDATED);
        when(requestUpdate.getId()).thenReturn(ID);
        when(epicRepository.findById(ID)).thenReturn(Optional.of(epicEntityFromDB));
        when(epicRepository.save(any())).thenReturn(updatedEntity);
        EpicDto result = epicService.update(requestUpdate);
        assertEquals(TEST_NAME_UPDATED, result.getName());
    }

    @Test
    @DisplayName("Negative update Epic")
    void updateEpicShouldThrowException() {
        when(epicRepository.findById(ID)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> epicService.update(requestUpdate));
    }

    @Test
    @DisplayName("Positive show list of epics with pagination")
    void getListOfEpicsShouldReturnListOfEntities() {
         List<EpicEntity> epicEntityList = List.of(getEpicEntity(), getEpicEntity(), getEpicEntity());
         when(epicRepository.findAll(PageRequest.of(PAGE_NUMBER, PAGE_SIZE))).thenReturn(new PageImpl<>(epicEntityList));
         when(pageUtil.pageSizeControl(PAGE_SIZE)).thenReturn(PAGE_SIZE);
         List<EpicDto> dtoList= epicService.getListOfEpics(PAGE_SIZE, PAGE_NUMBER);
         assertEquals(3, dtoList.size());
    }

    @Test
    @DisplayName("Positive findById Epic")
    void findByIdEpicShouldReturnEntity() {
        when(epicRepository.findById(ID)).thenReturn(Optional.of(getEpicEntity()));
        EpicDto result = epicService.findById(ID);
        assertEquals(ID,result.getId());
    }

    @Test
    @DisplayName("Negative findById Epic")
    void findByIdEpicShouldThowsExeption() {
        when(epicRepository.findById(ID)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, ()-> epicService.findById(ID));
    }

    @Test
    @DisplayName("Positive findByName Epic")
    void findByNameShouldReturnPageDto() {
        List<EpicEntity> listOfEntities = List.of(getEpicEntity(), getEpicEntity(), getEpicEntity());
        Page<EpicEntity> epicEntityPage = new PageImpl<>(listOfEntities,
                Pageable.ofSize(PAGE_SIZE).withPage(PAGE_NUMBER), listOfEntities.size());
        when(epicRepository.findByName(NAME.toUpperCase(),
                Pageable.ofSize(PAGE_SIZE).withPage(PAGE_NUMBER))).thenReturn(epicEntityPage);
        when(pageUtil.pageSizeControl(PAGE_SIZE)).thenReturn(PAGE_SIZE);
        PageDto<EpicDto> result = epicService.findByName(NAME, PAGE_SIZE, PAGE_NUMBER);
        assertEquals(3, result.getTotal());
    }
}