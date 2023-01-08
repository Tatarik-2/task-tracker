package ewp.tasktracker.service.workload;


import ewp.tasktracker.api.dto.workload.CreateWorkloadRq;
import ewp.tasktracker.api.dto.workload.UpdateWorkloadRq;
import ewp.tasktracker.api.dto.workload.WorkloadDto;
import ewp.tasktracker.entity.WorkloadEntity;
import ewp.tasktracker.entity.common.Status;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.WorkloadRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.persistence.RollbackException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class WorkloadServiceImplTest {

    @MockBean
    private WorkloadRepository workloadRepository;
    @Mock
    private CreateWorkloadRq createWorkloadRq;
    @Spy
    private UpdateWorkloadRq updateWorkloadRq;

    @Autowired
    private WorkloadServiceImpl service;

    private static final String NAME = "Test name";
    private static final String AUTHOR_ID = "Author ID";
    private static final String ID = "123456789123456789123456789123456789";
    private static final String TEST_NAME_UPDATED = "New test name";
    private static final int LIST_SIZE = 5;
    private static final int PAGE_NUMBER = 0;
    private static final int PAGE_SIZE = 5;

    @Test
    @DisplayName("The method create() in the WorkloadServiceImpl works correctly")
    void createShouldReturnCreatedEntity() {
        WorkloadEntity testEntity = prepareTestEntity();

        when(createWorkloadRq.toEntity()).thenReturn(testEntity);
        when(workloadRepository.save(any())).thenReturn(testEntity);

        WorkloadDto workloadDto = service.create(createWorkloadRq);
        assertThat(workloadDto.getName(), equalTo(NAME));
        assertThat(workloadDto.getAuthorId(), equalTo(AUTHOR_ID));
    }

    @Test
    @DisplayName("The method create() in the WorkloadServiceImpl throw RollbackException")
    void createShouldThrowException() {
        when(workloadRepository.save(any())).thenThrow(new RollbackException(""));

        assertThrows(RollbackException.class, () -> service.create(createWorkloadRq));
    }

    @Test
    @DisplayName("The method update() in the WorkloadServiceImpl works correctly")
    void updateShouldReturnUpdatedEntity() {
        WorkloadEntity testEntityInDb = prepareTestEntity();
        WorkloadEntity testEntityModify = prepareTestEntity();

        testEntityModify.setName(TEST_NAME_UPDATED);

//        when(updateWorkloadRq.getId()).thenReturn(ID);//одинаковый результат со след строчкой кода
        doReturn(ID).when(updateWorkloadRq).getId();
        when(workloadRepository.findById(ID)).thenReturn(Optional.of(testEntityInDb));
        when(workloadRepository.save(any())).thenReturn(testEntityModify);

        WorkloadDto workloadDto = service.update(updateWorkloadRq);

        assertThat(workloadDto.getName(), equalTo(TEST_NAME_UPDATED));
    }


    @Test
    @DisplayName("The method getById() in the WorkloadServiceImpl throw ResourceNotFoundException")
    void getByIdShouldThrowException() {
        when(workloadRepository.findById(ID)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.findById(ID));
    }

    @Test
    @DisplayName("The method findById() in the WorkloadServiceImpl works correctly")
    void getByIdShouldReturnEntity() {
        WorkloadEntity workloadEntity = prepareTestEntity();

        when(workloadRepository.findById(ID)).thenReturn(Optional.of(workloadEntity));

        WorkloadDto dto = service.findById(ID);

        assertThat(dto.getName(), equalTo(NAME));
    }

    @Test
    @DisplayName("The method findAll() in the WorkloadServiceImpl throw ResourceNotFoundException")
    void findAllShouldReturnEntityList() {
        List<WorkloadEntity> entityList = new ArrayList<>();
        for (int i = 0; i < LIST_SIZE; i++) {
            entityList.add(WorkloadEntity.builder()
                    .name(NAME + i)
                    .status(Status.ACTIVE)
                    .authorId(AUTHOR_ID + i)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build());
        }
        PageRequest pageRequest = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);

        when(workloadRepository.findAll(pageRequest)).thenReturn(new PageImpl<>(entityList));
//        doReturn(new PageImpl<>(entityList)).when(workloadRepository).findAll(pageRequest);
//можно и так
        List<WorkloadDto> dtoList = service.findAll(PAGE_SIZE, PAGE_NUMBER);

        assertThat(dtoList.size(), equalTo(LIST_SIZE));
    }

    private WorkloadEntity prepareTestEntity() {
        WorkloadEntity testEntity = new WorkloadEntity(NAME, Status.ACTIVE, AUTHOR_ID, LocalDateTime.now(), LocalDateTime.now());
        testEntity.setId(ID);

        return testEntity;
    }
}