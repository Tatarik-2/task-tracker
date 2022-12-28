package ewp.tasktracker.service.project;

import ewp.tasktracker.api.dto.project.CreateProjectRq;
import ewp.tasktracker.api.dto.project.ProjectDto;
import ewp.tasktracker.api.dto.project.UpdateProjectRq;
import ewp.tasktracker.api.util.PageUtil;
import ewp.tasktracker.entity.ProjectEntity;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final PageUtil pageUtil;

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
    public List<ProjectDto> findAll(Integer pageSize, Integer pageNumber) {
        Integer size = pageUtil.pageSizeControl(pageSize);
        return projectRepository.findAll(PageRequest.of(pageNumber, size))
                .stream().map(ProjectDto::new).collect(Collectors.toList());
    }

    @Override
    public ProjectDto updateProject(UpdateProjectRq dto) {
        ProjectEntity projectEntity = projectRepository.findById(dto.getId()).orElseThrow(() ->
                new ResourceNotFoundException("Project not found, id: " + dto.getId()));
        ProjectEntity projectEntityNew = dto.updateProject(projectEntity, dto);
        ProjectEntity resultEntity = projectRepository.save(projectEntityNew);
        return new ProjectDto(resultEntity);
    }
}
