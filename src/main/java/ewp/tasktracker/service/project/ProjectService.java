package ewp.tasktracker.service.project;

import ewp.tasktracker.api.dto.project.CreateProjectRq;
import ewp.tasktracker.api.dto.project.ProjectDto;

import java.util.List;


public interface ProjectService {
    ProjectDto create(CreateProjectRq dto);

    ProjectDto findById(String id);

    List<ProjectDto> findAll();
}
