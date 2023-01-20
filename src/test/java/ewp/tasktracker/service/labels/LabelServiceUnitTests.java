package ewp.tasktracker.service.labels;


import ewp.tasktracker.api.dto.label.CreateLabelRq;
import ewp.tasktracker.api.dto.label.LabelsDto;
import ewp.tasktracker.api.dto.label.UpdateLabelRq;
import ewp.tasktracker.api.util.PageUtil;
import ewp.tasktracker.entity.LabelsEntity;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.LabelsRepository;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class LabelServiceUnitTests {
    @Autowired
    private LabelsServiceImpl service;
    @MockBean
    private LabelsRepository labelsRepository;

    @MockBean
    PageUtil pageUtil;
    @Mock
    private UpdateLabelRq updateLabelRq;
    @Mock
    private CreateLabelRq createLabelRq;

    private static final String ID = "420";
    private static final String TEXT = "blablabla";
    private static final String TEXT_UPD = "UPDATED";
    private static final String AUTHOR_ID = "34";
    private static final String TASK_ID = "34";
    private static final int PAGE_NUMBER = 0;
    private static final int PAGE_SIZE = 20;


    @Test
    @DisplayName("Positive create Labels")
    void saveLabelsShouldReturnEntity() {
        LabelsEntity testEntity = getLabelsEntity();
        when(createLabelRq.toEntity()).thenReturn(testEntity);
        when(labelsRepository.save(any())).thenReturn(testEntity);
        LabelsDto result = service.save(createLabelRq);
        Assertions.assertAll(
                () -> assertEquals(LabelsDto.class, result.getClass()),
                () -> assertEquals(TEXT, result.getText()),
                () -> assertEquals(AUTHOR_ID, result.getAuthorId()),
                () -> assertEquals(TASK_ID, result.getTaskId()),
                () -> assertEquals(ID, result.getId())
        );
    }

    @Test
    @DisplayName("positive getById")
    void getByIdShouldReturnEntity(){
        when(labelsRepository.findById(ID)).thenReturn(Optional.of(getLabelsEntity()));
        LabelsDto result = service.findById(ID);
        assertEquals(ID, result.getId());
    }

    @Test
    @DisplayName("negative finById")
    void getByIdShouldThrowException(){
        when(labelsRepository.findById(ID)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.findById(ID));
    }

    @Test
    @DisplayName("positive getAll")
    void findAllShouldReturnListOfEntities(){
        List<LabelsEntity> entitiesList = List.of(getLabelsEntity(), getLabelsEntity(), getLabelsEntity());
        when(labelsRepository.findAll(PageRequest.of(PAGE_NUMBER, PAGE_SIZE))).thenReturn(new PageImpl<>(entitiesList));
        when(pageUtil.pageSizeControl(PAGE_SIZE)).thenReturn(PAGE_SIZE);
        List<LabelsDto> dtoList = service.findAll(PAGE_SIZE, PAGE_NUMBER);
        assertEquals(3, dtoList.size());
    }

    @Test
    @DisplayName("Positive update label")
    void updateLabelsShouldReturnEntity(){
        LabelsEntity entityFromDb = getLabelsEntity();
        LabelsEntity updatedEntity = getLabelsEntity();
        updatedEntity.setText(TEXT_UPD);
        when(updateLabelRq.getId()).thenReturn(ID);
        when(labelsRepository.findById(ID)).thenReturn(Optional.of(entityFromDb));
        when(labelsRepository.save(any())).thenReturn(updatedEntity);
        LabelsDto result = service.update(updateLabelRq);
        assertEquals(TEXT_UPD, result.getText());
    }

    @Test
    @DisplayName("Negative update label")
    void updateLabelShouldThrowException(){
        when(labelsRepository.findById(ID)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.findById(ID));
    }

    private LabelsEntity getLabelsEntity() {
        LabelsEntity testEntity = new LabelsEntity(TEXT, AUTHOR_ID, TASK_ID);
        testEntity.setId(ID);
        return testEntity;
    }
}
