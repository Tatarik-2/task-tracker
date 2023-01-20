package ewp.tasktracker.service.project;

import ewp.tasktracker.api.dto.project.CreateProjectRq;
import ewp.tasktracker.api.dto.project.ProjectDto;
import ewp.tasktracker.api.dto.project.UpdateProjectRq;
import ewp.tasktracker.entity.ProjectEntity;
import ewp.tasktracker.entity.common.Status;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.ProjectRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@DisplayName("Projects Service Tests")
public class ProjectServiceImplTest {
    @MockBean
    private ProjectRepository projectRepository;
    @Mock
    private CreateProjectRq requestCreate;
    @Mock
    private UpdateProjectRq requestUpdate;

    @Autowired
    private ProjectServiceImpl service;

    private static final String NAME = null;//"Test name";
    private static final String ID = "12345.#$;,we[fgwmegm[/.";
    private static final String DESC = "DescTest";
    private static final String AUTHORID = "X";
    private static final String WORKLOADID = "XII";
    private static final String TEST_NAME_UPDATED = null;

    @Test
    @DisplayName("Create Project")
    void saveShouldReturnCreatedEntity() {
        ProjectEntity testEntity = prepareTestEntity();

        when(requestCreate.toEntity()).thenReturn(testEntity);
        when(projectRepository.save(any())).thenReturn(testEntity);

        ProjectDto projectDto = service.create(requestCreate);
        Assertions.assertAll(
                () -> assertEquals(ProjectDto.class, projectDto.getClass()),
                () -> assertEquals(NAME, projectDto.getName()),
                () -> assertEquals(DESC, projectDto.getDescription()),
                () -> assertEquals(AUTHORID, projectDto.getAuthorId()),
                () -> assertEquals(WORKLOADID, projectDto.getWorkloadId()),
                () -> assertEquals(ID, projectDto.getId())
        );
    }
    @Test
    @DisplayName("Update Project")
    void updateShouldReturnUpdatedEntity() {
        ProjectEntity testEntityInDb = prepareTestEntity();
        ProjectEntity testEntityModify = prepareTestEntity();

        testEntityModify.setName(TEST_NAME_UPDATED);

        when(requestUpdate.getId()).thenReturn(ID);
        when(projectRepository.findById(ID)).thenReturn(Optional.of(testEntityInDb));
        when(projectRepository.save(any())).thenReturn(testEntityModify);

        ProjectDto projectDto = service.updateProject(requestUpdate);

        assertThat(projectDto.getName(), equalTo(TEST_NAME_UPDATED));
    }

    @Test
    @DisplayName("Negative ProjectID")
    void getByIdShouldThrowException() {
        when(projectRepository.findById(ID)).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> service.findById(ID));
    }
    @Test
    @DisplayName("Get Project by ID")
    void findByIdProjectShouldReturnEntity() {
        when(projectRepository.findById(ID)).thenReturn(Optional.of(prepareTestEntity()));
        ProjectDto result = service.findById(ID);
        Assertions.assertEquals(ID, result.getId());
    }

    private ProjectEntity prepareTestEntity() {
        ProjectEntity testEntity = new ProjectEntity(NAME, DESC, Status.ACTIVE, AUTHORID, WORKLOADID);
        testEntity.setId(ID);
        testEntity.setCreatedAt(LocalDateTime.now());
        testEntity.setUpdatedAt(LocalDateTime.now());

        return testEntity;
    }
}
