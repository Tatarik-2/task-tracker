package ewp.tasktracker.service;

import ewp.tasktracker.api.dto.CreateProjectRq;
import ewp.tasktracker.api.dto.ProjectDto;
import ewp.tasktracker.entity.ProjectEntity;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService{
    private final ProjectRepository projectRepository;

    @Override
    public ProjectDto create(CreateProjectRq dto) {
        ProjectEntity entity = projectRepository.save(dto.toEntity());
        return new ProjectDto(entity);
    }

    @Override
    public ProjectDto findById(String id) {
        ProjectEntity entity = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found, id: " + id));
        return new ProjectDto(entity);
    }

    @Override
    public List<ProjectDto> findAll() {
        return projectRepository.findAll().stream().map(ProjectDto::new).collect(Collectors.toList());
    }
}
