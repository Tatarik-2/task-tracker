package ewp.tasktracker.service;

import ewp.tasktracker.api.dto.CreateProjectRq;
import ewp.tasktracker.api.dto.ProjectDto;
import ewp.tasktracker.entity.ProjectEntity;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.ProjectRepository;
import liquibase.pro.packaged.P;
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
        return new ProjectDto(projectRepository.save(dto.toEntity()));
    }

    @Override
    public ProjectDto findById(String id) {
        return new ProjectDto(projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found, id: " + id)));
    }

    @Override
    public List<ProjectDto> findAll() {
        return projectRepository.findAll().stream().map(ProjectDto::new).collect(Collectors.toList());
    }
}
