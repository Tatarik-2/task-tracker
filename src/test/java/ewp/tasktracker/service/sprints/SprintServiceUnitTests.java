package ewp.tasktracker.service.sprints;

import ewp.tasktracker.api.dto.sprint.CreateSprintRq;
import ewp.tasktracker.api.dto.sprint.SprintDto;
import ewp.tasktracker.api.dto.sprint.UpdateSprintRq;
import ewp.tasktracker.api.util.PageUtil;
import ewp.tasktracker.entity.SprintEntity;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.SprintRepository;
import ewp.tasktracker.service.sprint.SprintServiceImpl;
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
public class SprintServiceUnitTests {
    @MockBean
    private SprintRepository historyRepository;
    @MockBean
    private PageUtil pageUtil;
    @Mock
    private CreateSprintRq requestCreate;
    @Mock
    private UpdateSprintRq requestUpdate;

    @Autowired
    private SprintServiceImpl service;

    private static final String NAME = "SPRINT";
    private static final String NAME_2 = "SPRINT2";
    private static final String SUPERSPRINTID = "123412341234";
    private static final String AUTHORID = "123412341234";
    private static final LocalDateTime STARTAT = LocalDateTime.MIN;
    private static final LocalDateTime ENDAT = LocalDateTime.MAX;
    private static final String ID = "123412341234";
    private static final int PAGE_NUMBER = 0;
    private static final int PAGE_SIZE = 20;

    @Test
    @DisplayName("Positive create Sprint")
    void saveSprintShouldReturnEntity() {
        SprintEntity testEntity = getSprintEntity();
        when(requestCreate.toEntity()).thenReturn(testEntity);
        when(historyRepository.save(any())).thenReturn(testEntity);
        SprintDto result = service.save(requestCreate);
        Assertions.assertAll(
                () -> assertEquals(SprintDto.class, result.getClass()),
                () -> assertEquals(NAME, result.getName()),
                () -> assertEquals(SUPERSPRINTID, result.getSuperSprintId()),
                () -> assertEquals(AUTHORID, result.getAuthorId()),
                () -> assertEquals(ID, result.getId())
        );
    }

    @Test
    @DisplayName("Positive findById Sprint")
    void findByIdSprintShouldReturnEntity() {
        when(historyRepository.findById(ID)).thenReturn(Optional.of(getSprintEntity()));
        SprintDto result = service.findById(ID);
        assertEquals(ID, result.getId());
    }

    @Test
    @DisplayName("Negative findById Sprint")
    void findByIdSprintShouldThrowException() {
        when(historyRepository.findById(ID)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.findById(ID));
    }

    @Test
    @DisplayName("Positive show all Histories(pagination)")
    void findAllHistoriesShouldReturnListOfEntities() {
        List<SprintEntity> listOfEntities = List.of(getSprintEntity(), getSprintEntity(), getSprintEntity());
        when(historyRepository.findAll(PageRequest.of(PAGE_NUMBER, PAGE_SIZE))).thenReturn(new PageImpl<>(listOfEntities));
        when(pageUtil.pageSizeControl(PAGE_SIZE)).thenReturn(PAGE_SIZE);
        List<SprintDto> dtoList = service.findAll(PAGE_SIZE, PAGE_NUMBER);
        assertEquals(3, dtoList.size());
    }

    @Test
    @DisplayName("Positive update Sprint")
    void updateSprintShouldReturnEntity() {
        SprintEntity historyEntityFromDB = getSprintEntity();
        SprintEntity updatedEntity = getSprintEntity();
        updatedEntity.setName(NAME_2);
        when(requestUpdate.getId()).thenReturn(ID);
        when(historyRepository.findById(ID)).thenReturn(Optional.of(historyEntityFromDB));
        when(historyRepository.save(any())).thenReturn(updatedEntity);
        SprintDto result = service.update(requestUpdate);
        assertEquals(NAME_2, result.getName());
    }

    @Test
    @DisplayName("Negative update Sprint")
    void updateSprintShouldThrowException() {
        when(historyRepository.findById(ID)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.update(requestUpdate));
    }

    private SprintEntity getSprintEntity() {
        SprintEntity entity = new SprintEntity();
        entity.setName(NAME);
        entity.setSuperSprintId(SUPERSPRINTID);
        entity.setAuthorId(AUTHORID);
        entity.setStartAt(STARTAT);
        entity.setEndAt(ENDAT);
        entity.setId(ID);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }
}
