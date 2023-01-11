package ewp.tasktracker.service.project;

import ewp.tasktracker.api.dto.project.CreateProjectRq;
import ewp.tasktracker.api.dto.project.ProjectDto;
import ewp.tasktracker.api.dto.project.UpdateProjectRq;

import java.util.List;


public interface ProjectService {
    ProjectDto create(CreateProjectRq dto);

    ProjectDto findById(String id);

    List<ProjectDto> findAll(Integer pageSize, Integer pageNumber);

    ProjectDto updateProject(UpdateProjectRq dto);
}
